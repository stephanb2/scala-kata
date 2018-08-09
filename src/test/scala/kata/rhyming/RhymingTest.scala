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
    assertResult("!EXCLAMATION-POINT") {dict(0)(0)}
  }

  it should "break phonemes to a lsit" in {
    val expected = List("AACHEN",  "AA1", "K", "AH0", "N")
    assertResult(expected) {fixture.cmuDict.filter(_.head == "AACHEN").head}
  }

  "getTrailingVC" should "find the trailing vowel + consonants" in {
    val word = fixture.cmuDict.filter(_(0) == "AACHEN").head
    assertResult("AH0N") {Rhyming.getTrailingVC(word)}
  }

  "getRhymesDict" should "list the rhymes for each word" in {
    val rhymes = Rhyming.getRhymesDict(fixture.cmuDict)
    val expected = List("AACHEN", "AH0N")
    assertResult(expected) {rhymes.filter(_.head == "AACHEN").head}
  }
}
