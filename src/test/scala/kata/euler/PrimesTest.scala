package kata.euler

import org.scalatest.FlatSpec

import scala.collection.immutable.{NumericRange, SortedSet}

class PrimesTest extends FlatSpec {

  "sieve" should "return prime numbers to 23" in {
    val max = 24L
    val remaining = SortedSet[Long]() ++ NumericRange[Long](2L, max, 1L)
    val expectedPrimes: List[Long] = List(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L)

    assertResult(expectedPrimes) { Primes.sieve(remaining, List[Long](), max)}
  }

  "getPrimes" should "return prime numbers to 29" in {
    val expectedPrimes: List[Long] = List(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L)

    assertResult(expectedPrimes) { Primes.getPrimes(29L)}
  }
}
