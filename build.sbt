import com.typesafe.sbt.packager.docker.Cmd

lazy val dockerSettings = Seq(
  dockerBaseImage    := "azul/zulu-openjdk-alpine:17-jre-headless-latest",
  dockerUpdateLatest := true,
  dockerCommands     := dockerCommands.value.flatMap {
    case cmd @ Cmd("FROM", _, "as", "mainstage") =>
      List(
        cmd,
        Cmd("RUN", "apk --update-cache upgrade && rm -rf /var/cache/apk/*"),
        Cmd(
          "ADD",
          "https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.21.0/opentelemetry-javaagent.jar",
          "/opt/opentelemetry-javaagent.jar",
        ),
        Cmd("RUN", "chmod 644 /opt/opentelemetry-javaagent.jar"),
        Cmd("ENV", "JAVA_TOOL_OPTIONS", "-javaagent:/opt/opentelemetry-javaagent.jar"),
      )
    case other                                   => List(other)
  },
)

lazy val root = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging, DockerPlugin, AshScriptPlugin)
  .settings(
    name                                   := "weekend-wizard",
    organization                           := "com.hivemindtechnologies.weekendwizard",
    version                                := "0.1.0-SNAPSHOT",
    scalaVersion                           := "3.2.1",
    libraryDependencies                   ++= Seq(
      "dev.zio" %% "zio"               % "2.0.5",
      "io.d11"  %% "zhttp"             % "2.0.0-RC11",
      "dev.zio" %% "zio-opentelemetry" % "3.0.0-RC1",
      "dev.zio" %% "zio-test"          % "2.0.5" % Test,
      "dev.zio" %% "zio-test-sbt"      % "2.0.5" % Test,
    ),
    dockerSettings,
    dockerExposedPorts                    ++= Seq(8080),
    Compile / packageDoc / publishArtifact := false,
    testFrameworks                         += new TestFramework("zio.test.sbt.ZTestFramework"),
  )
