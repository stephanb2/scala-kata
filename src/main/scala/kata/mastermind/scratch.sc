import scala.collection.mutable

val la = List(7, 12, 36, 56)
val lc = List(1 ,2, 2, 3)

la.zip(lc).filter(_._2 == 2).map(_._1)

val sa = mutable.HashSet(12, 56)
val found = la.find(sa.contains(_))
found.getOrElse(-1)

// D. Knuth example secret = 3632
