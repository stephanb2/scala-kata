import scala.collection.immutable.{NumericRange, SortedSet}

// get multiples of k up to m
def mulb(i: Long = 1): Stream[Long] = i #:: mulb(i).map(_ + i)
mulb(3).take(4).toList
mulb(3).take(1).toList.head


//mulb(3).filter(_ < 20L).toList

val k = 4L
val max = 100L
val mulc = NumericRange[Long](k, max, k).toList

val remaining = SortedSet[Long]() ++ NumericRange[Long](2L, max, 1L)

val ma = Map(1 -> 2, 2 -> 3, 5 -> 1)
val mb = Map(1 -> 4, 2 -> 2, 3 -> 1)

ma.keySet.diff(mb.keySet)

val mm =  ma.filter(x => ma.keySet.diff(mb.keySet).contains(x._1)) ++
  mb.filter(x => mb.keySet.diff(ma.keySet).contains(x._1))  ++
  ma.filter(x => ma.keySet.intersect(mb.keySet).contains(x._1))


'0'.toInt
