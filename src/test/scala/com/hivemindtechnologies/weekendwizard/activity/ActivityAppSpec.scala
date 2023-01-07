package com.hivemindtechnologies.weekendwizard.activity

import zhttp.http.*
import zio.test.*
import zio.test.Assertion.{equalTo, isOneOf}

object ActivityAppSpec extends ZIOSpecDefault:

  private val app = ActivityApp()

  def spec: Spec[Any, Any] = suite("Weekend Activity")(
    test("should return an activity") {
      val req = Request().setPath(!! / "weekend-activity")
      val res = app(req)
      assertZIO(res.map(_.status))(equalTo(Status.Ok)) &&
      assertZIO(res.flatMap(_.body.asString))(isOneOf(ActivityApp.activities))
    },
  )
