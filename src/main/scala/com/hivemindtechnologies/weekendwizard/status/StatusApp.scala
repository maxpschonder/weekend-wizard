package com.hivemindtechnologies.weekendwizard.status

import zhttp.http.*

object StatusApp:
  def apply(): Http[Any, Nothing, Request, Response] =
    Http.collect[Request] {
      // GET /livez
      case Method.GET -> !! / "livez" =>
        Response.text("Live")

      // GET /readyz
      case Method.GET -> !! / "readyz" =>
        Response.text("Ready")
    }
