package scratch

import org.scalatest.{FunSuite, Ignore}

@Ignore
class HelloTest extends FunSuite {

  test("Hello world") {
    val hello = Hello("world")
    assert(hello.toString === "Hello world")
  }
}
