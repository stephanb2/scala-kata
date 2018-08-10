package kata.rhyming

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

object Rhyming {

  type BaseDict = Map[String, List[String]]

  def readFile(filename: String): BaseDict = {
    Source.fromFile(filename).getLines.map(_.split("\\s+").toList)
      .filter(_.head.substring(0, 1) != "#") // skip comments
      .map(l => Tuple2(l.head, l.tail))
      .toMap
  }

  def getTrailingVC(word: List[String]): String = {
    val lastVowelPos = word.lastIndexWhere(s => s.takeRight(1).matches("\\d"))
    word.drop(lastVowelPos).mkString("")
  }


  def getRhymesDict(dict: BaseDict): BaseDict = {

    def buildRhymes(words:List[String], dict: BaseDict, result: mutable.HashMap[String, List[String]]): BaseDict = {
      words match {
        case Nil => result.map(x => (x._1, x._2.toList)).toMap
        case x::xs => {
          val rhyme = getTrailingVC(dict(x))
          result(rhyme) = if (result.contains(rhyme)) result(rhyme) :+ x else List(x)
          buildRhymes(xs, dict, result)
        }
      }
    }

    buildRhymes(dict.keys.toList, dict, mutable.HashMap.empty[String, List[String]])
  }


}
