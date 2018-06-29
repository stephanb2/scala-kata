import kata.mastermind.KeyUtils

val colourMap = Map("R" -> 1, "G" -> 2, "B" -> 3, "O" -> 4, "P" -> 5, "Y" -> 6)
colourMap.get("R")

val colours = "R G B O P Y"
"R G B P O".split(" ").indexWhere(_ == "R")

"R G B P O".split(" ").map(colourMap.getOrElse(_, -1)).toVector

"RGBP".toCharArray.map(_.toInt).toVector
"R G B P".replaceAll(" ","")

//"R G B P O".split(" ").map()

val list = List(1, 5, 6, 12)

list.find(_ == 6)


val vec = Vector(1, 5, 6, 12)
vec.indexWhere(_ == 6)
vec.indexWhere(_ == 2)

vec.take(2) ++ vec.drop(3)


val al = List(0, 1).flatMap(x => (0 to 4 - x).map(y => List(x, y))  )
al.last
al.dropRight(1) :+ List(4, 0)

val zero = '0'.toInt
BigInt(12).toString(6).toVector.map(_ - zero)

val pad = (v: Vector[Int]) =>  Vector.fill(4 - v.length)(1) ++ v

KeyUtils.blackWhiteHits(Vector(1,2), Vector(2, 1))

List(2, 5).min
