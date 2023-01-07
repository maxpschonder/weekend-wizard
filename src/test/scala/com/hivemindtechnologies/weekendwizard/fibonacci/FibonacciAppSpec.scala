package com.hivemindtechnologies.weekendwizard.fibonacci

import zhttp.http.*
import zio.test.*
import zio.test.Assertion.equalTo

object FibonacciAppSpec extends ZIOSpecDefault:

  private val app = FibonacciApp()

  def spec: Spec[Any, Any] = suite("Fibonacci")(
    test("should return 55 for 10") {
      val req = Request().setPath(!! / "fibonacci" / "10")
      val res = app(req)
      assertZIO(res.map(_.status))(equalTo(Status.Ok)) &&
      assertZIO(res.flatMap(_.body.asString))(equalTo("55"))
    },
    test("should return 0 for 0") {
      val req = Request().setPath(!! / "fibonacci" / "0")
      val res = app(req)
      assertZIO(res.map(_.status))(equalTo(Status.Ok)) &&
      assertZIO(res.flatMap(_.body.asString))(equalTo("0"))
    },
    test("should return response code 400 for invalid input") {
      val req = Request().setPath(!! / "fibonacci" / "invalid")
      val res = app(req)
      assertZIO(res.map(_.status))(equalTo(Status.BadRequest))
    },
  )
