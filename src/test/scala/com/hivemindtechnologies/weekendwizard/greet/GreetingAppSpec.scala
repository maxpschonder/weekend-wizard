package com.hivemindtechnologies.weekendwizard.greet

import zhttp.http.*
import zio.test.*
import zio.test.Assertion.equalTo

object GreetingAppSpec extends ZIOSpecDefault:

  private val app = GreetingApp()

  def spec: Spec[Any, Any] = suite("Application Status")(
    test("/greet endpoint should return \"Hello World!\"") {
      val req = Request().setPath(!! / "greet")
      val res = app(req)
      assertZIO(res.map(_.status))(equalTo(Status.Ok)) &&
      assertZIO(res.flatMap(_.body.asString))(equalTo("Hello World!"))
    },
    test("/greet/George endpoint should return \"Hello George!\"") {
      val req = Request().setPath(!! / "greet" / "George")
      val res = app(req)
      assertZIO(res.map(_.status))(equalTo(Status.Ok)) &&
      assertZIO(res.flatMap(_.body.asString))(equalTo("Hello George!"))
    },
    test("/greet?name=Lucy endpoint should return \"Hello Lucy!\"") {
      val req = Request(url = URL(!! / "greet").setQueryParams("name=Lucy"))
      val res = app(req)
      assertZIO(res.map(_.status))(equalTo(Status.Ok)) &&
      assertZIO(res.flatMap(_.body.asString))(equalTo("Hello Lucy!"))
    },
  )
