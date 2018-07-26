package kata.euler

import org.scalatest.FlatSpec

class TrianglePairsTest extends FlatSpec {

  "TrianglePairs" should "start with (1, 1)" in {
    val iter = new TrianglePairs(2)
    assert(iter.next() == (1, 1))
  }
}