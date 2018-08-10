package kata.rhyming

import org.scalatest.FlatSpec

class RhymingTest extends FlatSpec {

  def fixture =
  new {
    val dictFilename = "data/cmudict.0.6d.txt"
    val cmuDict = Rhyming.readFile(dictFilename)
  }

  "readFile" should "skip comments" in {
    val dict = Rhyming.readFile(fixture.dictFilename)
    assert(!dict.contains("##"))
  }

  it should "break phonemes to a list" in {
    val expected = List("AA1", "K", "AH0", "N")
    assertResult(expected) {fixture.cmuDict("AACHEN")}
  }

  "getTrailingVC" should "find the trailing vowel + consonants" in {
    val word = fixture.cmuDict("AACHEN")
    assertResult("AH0N") {Rhyming.getTrailingVC(word)}
  }

  "getRhymesDict" should "list the words for each rhyme" in {
    val rhymesDict = Rhyming.getRhymesDict(fixture.cmuDict)
    //val expected = List("AACHEN", "AH0N")
    assert(rhymesDict("AH0N").contains("AACHEN"))
    assert(rhymesDict("AH0N").contains("MUSICIAN"))
  }
}
