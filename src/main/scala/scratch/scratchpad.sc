
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

val li2 = List(12, 6)
li2.sum

li2.head

list match {
  case x :: xs => println(x + "---" + xs)
}

