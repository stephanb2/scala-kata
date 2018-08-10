package kata.rhyming

import scala.io.Source

object RhymingList {

  type BaseDict = List[List[String]]

  def readFile(filename: String): BaseDict = {
    Source.fromFile(filename).getLines.map(_.split("\\s+").toList)
      .filter(_.head.substring(0, 1) != "#") // skip comments
      .toList
  }

  def getTrailingVC(word: List[String]): String = {
    val lastVowelPos = word.lastIndexWhere(s => s.takeRight(1).matches("\\d"))
    word.drop(lastVowelPos).mkString("")
  }

  def getRhymesDict(dict: BaseDict): BaseDict = {
    dict.map(x => List(x.head, getTrailingVC(x.tail)))
  }


}
