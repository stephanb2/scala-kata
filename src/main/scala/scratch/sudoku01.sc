Range(1, 10).flatMap(x => Range(1, 10).map(y => (x, y)))

Range(1, 10).toSet

val mm = Range(0, 2).map(x => x -> 0).toMap
mm.size
mm(1)

val m2 = mm ++ Map(6 -> 2)

m2.values.min

val mv1 = Map((1,2) -> Set(1, 2), (1,5) -> Set(4))
val sizes = mv1.mapValues(_.size)
sizes.values.min
mv1.keys.head
mv1.values.head

for (v <- mv1.values.head.toList) yield {
  v + 1
}

val minIndex = sizes.filter(_._2 == 1).keys.head

//mv1.

val ll = List(1, 2, 3)
ll.contains(2)
ll :+ 6

ll.toSet - 1

val grid =
  """
    |1 . 7|. 8 5|. 9 .|
    |6 . .|7 . .|8 5 2|
    |8 . 5|6 3 .|. . 7|
    |------------------
    |3 5 .|. 1 6|. 7 .|
    |7 1 .|4 . 2|. . 8|
    |. 8 .|3 . .|9 6 1|
    |------------------
    |. 3 4|. . 8|7 . 5|
    |. . 8|5 7 3|1 . .|
    |. 7 1|. 2 .|6 8 .|
  """


grid.split("\n").filter("123456789.".toSet)

val Digits = "[1-9]".r

val step1 = for (c <- grid) yield {
  c match {
    case '.' => 0
    case Digits() => c.toString.toInt
    case _ => -1
  }
}

val step2 = step1.filterNot(_ == -1).zipWithIndex.filterNot(_._1 == 0)
