import com.typesafe.sbt.packager.docker.Cmd

lazy val dockerSettings = Seq(
  dockerBaseImage    := "azul/zulu-openjdk-alpine:17-jre-headless-latest",
  dockerUpdateLatest := true,
  dockerCommands := dockerCommands.value.flatMap {
    case cmd @ Cmd("FROM", _, "as", "mainstage") =>
      List(
        cmd,
        Cmd("RUN", "apk --update-cache upgrade && rm -rf /var/cache/apk/*"),
      )
    case other => List(other)
  },
)

val scala3Version = "3.2.1"
val http4sVersion = "1.0.0-M37"

lazy val root = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging, DockerPlugin, AshScriptPlugin)
  .settings(
    name         := "weekend-wizard",
    version      := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "org.http4s"    %% "http4s-dsl"          % http4sVersion,
      "org.http4s"    %% "http4s-ember-server" % http4sVersion,
      "org.http4s"    %% "http4s-ember-client" % http4sVersion,
      "ch.qos.logback" % "logback-classic"     % "1.3.5",
    ),
    dockerSettings,
    dockerExposedPorts ++= Seq(8080),
  )
