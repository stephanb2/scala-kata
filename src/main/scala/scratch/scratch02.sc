import scala.collection.mutable

val la = List(7, 12, 36, 56)
val lc = List(1 ,2, 2, 3)

la.zip(lc).filter(_._2 == 2).map(_._1)

val sa = mutable.HashSet(12, 56)
val found = la.find(sa.contains(_))
found.getOrElse(-1)

val va = Vector(1, 2, 4, 5)
val slice = va.indexOf(2)
val split = va.splitAt(va.indexOf(2))
split._1 ++ split._2.tail
va.contains(2)


val splitb = va.splitAt(va.indexOf(3))
"1 2".split("\\s+").map(_.toInt).toList