package kata.rhyming

import kata.rhyming.Rhyming.BaseDict

import scala.util.Random

object Limerick extends App {

  def getRhymingWords(word: List[String], rhymeDict: BaseDict): List[String] = {
    rhymeDict(Rhyming.getTrailingVC(word))
  }

  val ref = """The limerick packs laughs anatomical
                |Into space that is quite economical.
                |But the good ones I've seen
                |So seldom are clean
                |And the clean ones so seldom are comical""".stripMargin

  val input = """The limerick packs laughs %s
                |Into space that is quite %s.
                |But the good ones I've seen
                |So seldom are %s
                |And the %s ones so seldom are comical""".stripMargin

  val cmuDict = Rhyming.readFile("data/cmudict.0.6d.txt")
  val rhymeDict = Rhyming.getRhymesDict(cmuDict)

  val rhyme1 = Random.shuffle( getRhymingWords(cmuDict("COMICAL"), rhymeDict) )
  val rhyme2 = Random.shuffle( getRhymingWords(cmuDict("CLEAN"), rhymeDict) )

  print(input.format(rhyme1(0), rhyme1(1), rhyme2.head, rhyme2.head))

}
