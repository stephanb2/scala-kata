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

  // TODO: this is slower. Don't use the modulo operator!
  @tailrec
  def fastSieve(remainingInts: Vector[Long], primes:List[Long], max: Long): List[Long] = {
    remainingInts.size match {
      case 0 => primes
      case _ => {
        val k = remainingInts.head
        fastSieve(remainingInts.filter(_ % k != 0), primes :+ k , max)
      }
    }
  }

  def getPrimes(max: Long): List[Long] = {
    //val oddIntegers = SortedSet[Long]() ++ NumericRange[Long](3L, max + 1, 2L)
    //return 2L :: sieve(oddIntegers, List[Long](), max + 1)
    val oddIntegers = NumericRange[Long](3L, max + 1, 2L).toVector
    return 2L :: fastSieve(oddIntegers, List[Long](), max + 1)
  }

  @tailrec
  def primeDec(input: Long, primes: List[Long], factors: SparseVec): SparseVec = {
    primes match {
      case Nil => if (input == 1L)
        factors
      else
        factors.inc(input)

      case x::xs => if (input % x == 0)
        primeDec(input / x, primes, factors.inc(x))
      else
        primeDec(input, xs, factors)
    }
  }

  def primeDec(input: Long): SparseVec = {
    val primes = getPrimes(math.sqrt(input.toDouble).floor.toLong)
    primeDec(input, primes, SparseVec())
  }

}
