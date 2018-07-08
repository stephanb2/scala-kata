def getFirst(map: Map[Int, Int]): Int = {
  map.size match {
    case 0 => -1
    case _ => map.head._2
  }
}

getFirst(Map())
getFirst(Map(6 -> 32))