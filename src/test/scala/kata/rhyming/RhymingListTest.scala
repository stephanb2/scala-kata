package kata.rhyming

import org.scalatest.FlatSpec

class RhymingListTest extends FlatSpec {

  def fixture =
    new {
      val dictFilename = "data/cmudict.0.6d.txt"
      val cmuDict = RhymingList.readFile(dictFilename)
    }

  "readFile" should "skip comments" in {
    val dict = RhymingList.readFile(fixture.dictFilename)
    assertResult("!EXCLAMATION-POINT") {dict(0)(0)}
  }

  it should "break phonemes to a list" in {
    val expected = List("AACHEN",  "AA1", "K", "AH0", "N")
    assertResult(expected) {fixture.cmuDict.filter(_.head == "AACHEN").head}
  }

  "getTrailingVC" should "find the trailing vowel + consonants" in {
    val word = fixture.cmuDict.filter(_(0) == "AACHEN").head
    assertResult("AH0N") {RhymingList.getTrailingVC(word)}
  }

  "getRhymesDict" should "list the rhymes for each word" in {
    val rhymes = RhymingList.getRhymesDict(fixture.cmuDict)
    val expected = List("AACHEN", "AH0N")
    assertResult(expected) {rhymes.filter(_.head == "AACHEN").head}
  }

  it should "allow to list words given a rhyme" in {
    val rhyme = "AH0N"
    val rhymesDict = RhymingList.getRhymesDict(fixture.cmuDict)
    val result = rhymesDict.filter(_.last == rhyme).map(_.head)
    assert(result.contains("AACHEN"))
    assert(result.contains("MUSICIAN"))
  }

  "getRhymingWords" should "list words rhyming with a given word" in {
    val input = "ORANGE"
    val rhymesDict = RhymingList.getRhymesDict(fixture.cmuDict)
    val result = RhymingList.getRhymingWords(input, rhymesDict)
    assert(result.contains("CHALLENGE"))
    assert(result.contains("SCAVENGE"))
  }
}
