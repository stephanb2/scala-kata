package kata.euler

import scala.annotation.tailrec
import scala.collection.immutable.{NumericRange, SortedSet}

object Primes {

  @tailrec
  def sieve(remainingInts: SortedSet[Long], primes:List[Long], max: Long): List[Long] = {
    remainingInts.size match {
      case 0 => primes
      case _ => {
        val k = remainingInts.head
        val multiples = NumericRange[Long](k, max, k).toSet
        sieve(remainingInts -- multiples, primes :+ k , max)
      }
    }
  }

  def getPrimes(max: Long): List[Long] = {
    val oddIntegers = SortedSet[Long]() ++ NumericRange[Long](3L, max + 1, 2L)

    return 2L :: sieve(oddIntegers, List[Long](), max + 1)
  }

  @tailrec
  def primeDec(input: Long, primes: List[Long], factors: List[Long] = List()): List[Long] = {
    return List()
  }
}
