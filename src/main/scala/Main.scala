import cats.effect.*
import com.comcast.ip4s.*
import org.http4s.HttpRoutes
import org.http4s.dsl.io.*
import org.http4s.implicits.*
import org.http4s.ember.server.*
import org.http4s.server.middleware.Logger

object Main extends IOApp:

  private def fibonacci(n: Long): Long = n match
    case 0 => 0
    case 1 => 1
    case n => fibonacci(n - 1) + fibonacci(n - 2)

  private val app = HttpRoutes
    .of[IO] {
      case GET -> Root / "fibonacci" / IntVar(n) =>
        val result = fibonacci(n)
        Ok(s"The result is: $result")

      case GET -> Root / "weekend-activity" =>
        val activities = List(
          "Have a staring contest with all the artwork in a museum",
          "Have a watermelon seed spitting contest",
          "Go to a public place and do the chicken dance",
          "Try on all the hard hats in a hardware store",
          "Have a conversation with all the animals in a pet store",
          "Play with all the toys in a toy store",
          "Try on all the sports jerseys in a sporting goods store",
          "Try on all the wigs in a department store",
          "Walk around in a pair of heels in a shoe store",
          "Try on all the hats in a clothing store",
          "Have a sandcastle building contest at the beach",
          "Have a floatie race in a pool",
          "Have a cart race in a grocery store",
          "Have a frisbee throwing contest in a park",
          "Have a whispered conversation in a library",
          "Read all the ridiculous book titles out loud in a book store",
        )
        val activity = activities(scala.util.Random.nextInt(activities.length))
        Ok(activity)

      case GET -> Root / "health" =>
        Ok("OK")
    }
    .orNotFound

  def run(args: List[String]): IO[ExitCode] =
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(Logger.httpApp(true, false)(app))
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
