import scala.collection.immutable.{NumericRange, SortedSet}

// get multiples of k up to m
def mulb(i: Long = 1): Stream[Long] = i #:: mulb(i).map(_ + i)
mulb(3).take(4).toList

//mulb(3).filter(_ < 20L).toList

val k = 4L
val max = 100L
val mulc = NumericRange[Long](k, max, k).toList

val remaining = SortedSet[Long]() ++ NumericRange[Long](2L, max, 1L)

var grid: Array[Boolean] = Array.tabulate(5)(_ % 2 != 0)

//val abs = Stream.range(1, 10).zip(Stream.range(1, 10)).takeWhile(x => x._1 < x._2).map(x => x._1*x._2)
val abs = Stream.range(1, 10).zip(Stream.range(1, 10)).map(x => (x._1, x._2, x._1*x._2))


abs.take(5).toList
abs.takeWhile(x => x._1 < x._2).toList

math.sqrt(2)

val maxb = 5
val ll = List.range(1, maxb).flatMap(x => List.range(x+1, maxb).map(y => (x, y)))

ll.map(p => (p._1, p._2, p._1*p._1 + p._2*p._2)).filter(x => math.sqrt(x._3) < 10)