package kata.mastermind

import org.scalatest._


class KeyUtilsTest extends FlatSpec {

  behavior of "dropExactMatches"

   it should "return the original input when there are no perfect matches" in {
    val guess = Vector(1, 2, 3, 4)
    val secret = Vector(2, 3 ,4, 1)
    val result = KeyUtils.dropExactMatches(guess, secret)
    assertResult(guess) { result._1 }
    assertResult(secret) { result._2 }
  }

  it should "return input minus perfect matches" in {
    val guess = Vector(1, 2, 3, 4)
    val secret = Vector(1, 2 ,4, 1)
    val result = KeyUtils.dropExactMatches(guess, secret)
    assertResult(Vector(3, 4)) { result._1 }
    assertResult(Vector(4, 1)) { result._2 }
  }


  behavior of "countFuzzyMatches"

  it should "return matches in single occurence A" in {
    val guess = Vector(1, 2, 3, 4)
    val secret = Vector(2, 3 ,4, 1)
    assertResult(4) { KeyUtils.countFuzzyMatches(guess, secret) }
  }

  it should "return matches in single occurence B" in {
    val guess = Vector(1, 2, 3, 6)
    val secret = Vector(2, 3 ,3, 1)
    assertResult(3) { KeyUtils.countFuzzyMatches(guess, secret) }
  }

  it should "return matches with mutliple occurences" in {
    val guess = Vector(3, 2, 6, 3)
    val secret = Vector(1, 3 ,3, 6)
    assertResult(3) { KeyUtils.countFuzzyMatches(guess, secret) }
  }


  behavior of "blackWhiteHits"

  it should "match Plos One example 1: R B G Y | R O O B" in {
    val guess = KeyUtils.stringToVec("R G B Y") //Vector(1, 2, 3, 6)
    val secret = KeyUtils.stringToVec("R O O B") //Vector(1, 4, 4, 2)
    assertResult(List(1, 1)) { KeyUtils.blackWhiteHits(guess, secret) }
  }

  it should "match Plos One example 2: P O G Y" in {
    val guess = KeyUtils.stringToVec("P O G Y")
    val secret = KeyUtils.stringToVec("R O O B")
    assertResult(List(1, 0)) { KeyUtils.blackWhiteHits(guess, secret) }
  }

  it should "match Plos One example 5: R O B O " in {
    val guess = KeyUtils.stringToVec("R O B O")
    val secret = KeyUtils.stringToVec("R O O B")
    assertResult(List(2, 2)) { KeyUtils.blackWhiteHits(guess, secret) }
  }

  it should "find exact match" in {
    val guess = Vector(1, 0, 0, 2)
    val secret = Vector(1, 0, 0, 2)
    assertResult(List(4, 0)) { KeyUtils.blackWhiteHits(guess, secret) }
  }

}
