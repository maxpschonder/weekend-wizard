package com.hivemindtechnologies.weekendwizard.activity

import zhttp.http.*

object ActivityApp:
  val activities: List[String] = List(
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

  def apply(): Http[Any, Nothing, Request, Response] =
    Http.collect[Request] {
      // GET /greet
      case Method.GET -> !! / "weekend-activity" =>
        val activity = activities(scala.util.Random.nextInt(activities.length))
        Response.text(activity)
    }
