package com.hivemindtechnologies.weekendwizard

import com.hivemindtechnologies.weekendwizard.activity.ActivityApp
import com.hivemindtechnologies.weekendwizard.fibonacci.FibonacciApp
import com.hivemindtechnologies.weekendwizard.greet.GreetingApp
import com.hivemindtechnologies.weekendwizard.status.StatusApp
import zhttp.http.Middleware
import zhttp.service.Server
import zio.*
import zio.telemetry.opentelemetry.Tracing

object MainApp extends ZIOAppDefault:
  def run: ZIO[Environment with ZIOAppArgs with Scope, Any, Any] =
    Server
      .start(
        port = 8080,
        http = (GreetingApp() ++ ActivityApp() ++ FibonacciApp() ++ StatusApp()) @@ LoggingMiddleware.verbose,
      )
      .provide(Tracing.propagating)
