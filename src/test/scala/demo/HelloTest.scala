package demo

import org.scalatest.FunSuite

class HelloTest extends FunSuite {

  test("Hello world") {
    val hello = Hello("world")
    assert(hello.toString === "Hello world")
  }
}
