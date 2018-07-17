package kata.euler

import scala.collection.immutable.{NumericRange, SortedSet}

object Primes {

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

}
