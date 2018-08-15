package kata.rhyming

import scala.util.Random

object Limerick02 extends App {
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

  val cmuDict = RhymingList.readFile("data/cmudict.0.6d.txt")
  val longRhymes = RhymingList.getRhymesDict(cmuDict, 5)
  val shortRhymes = RhymingList.getRhymesDict(cmuDict, 3)

  val rhyme1 = Random.shuffle(RhymingList.getRhymingWords("COMICAL", longRhymes))
  val rhyme2 = Random.shuffle(RhymingList.getRhymingWords("SEEN", shortRhymes))

  print(input.format(rhyme1(0), rhyme1(1), rhyme2.head, rhyme2.head))

}
