import kata.euler.Primes

import scala.collection.immutable.{NumericRange, SortedSet}
// get multiples of k up to m

def mulb(i: Long = 1): Stream[Long] = i #:: mulb(i).map(_ + i)
mulb(3).take(4).toList

//mulb(3).filter(_ < 20L).toList

val k = 4L
val max = 100L
val mulc = NumericRange[Long](k, max, k).toList

val remaining = SortedSet[Long]() ++ NumericRange[Long](2L, max, 1L)
val result = Primes.sieve(remaining, List[Long](), max)
result
