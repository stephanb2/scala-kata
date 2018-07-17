package kata.euler

import org.scalatest.FlatSpec

import scala.collection.immutable.{NumericRange, SortedSet}

class PrimesTest extends FlatSpec {

  "Primes.sieve" should "return prime numbers to 20" in {
    val k = 4L
    val max = 20L
    val mulc = NumericRange[Long](k, max, k).toList

    val remaining = SortedSet[Long]() ++ NumericRange[Long](2L, max, 1L)
    val result = Primes.sieve(remaining, List[Long](), max)
  }

}
