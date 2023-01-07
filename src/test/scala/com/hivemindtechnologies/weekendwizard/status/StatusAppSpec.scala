package com.hivemindtechnologies.weekendwizard.status

import zhttp.http.*
import zio.test.*
import zio.test.Assertion.equalTo

object StatusAppSpec extends ZIOSpecDefault:

  private val app: Http[Any, Nothing, Request, Response] = StatusApp()

  def spec: Spec[Any, Any] = suite("Application Status")(
    test("readiness endpoint should return 200") {
      val req = Request().setPath(!! / "readyz")
      val res = app(req)
      assertZIO(res.map(_.status))(equalTo(Status.Ok))
    },
    test("liveness endpoint should return 200") {
      val req = Request().setPath(!! / "livez")
      val res = app(req)
      assertZIO(res.map(_.status))(equalTo(Status.Ok))
    },
  )
