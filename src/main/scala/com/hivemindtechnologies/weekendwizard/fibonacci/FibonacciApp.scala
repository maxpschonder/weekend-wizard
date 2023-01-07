package com.hivemindtechnologies.weekendwizard.fibonacci

import zhttp.http.*

object FibonacciApp:
  private def fibonacci(n: Int): BigInt =
    @scala.annotation.tailrec
    def fib(n: Int, acc1: BigInt, acc2: BigInt): BigInt = n match
      case 0 => acc1
      case 1 => acc2
      case _ => fib(n - 1, acc2, acc1 + acc2)

    fib(n, 0, 1)

  def apply(): Http[Any, Nothing, Request, Response] =
    Http.collect[Request] {
      // GET /fibonacci/<number>
      case Method.GET -> !! / "fibonacci" / number =>
        number.toIntOption match
          case Some(n) => Response.text(fibonacci(n).toString)
          case None    => Response.text("Invalid number").setStatus(Status.BadRequest)
    }
