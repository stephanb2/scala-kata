import scala.collection.mutable

// mulist = mutable.ListBuffer()

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

