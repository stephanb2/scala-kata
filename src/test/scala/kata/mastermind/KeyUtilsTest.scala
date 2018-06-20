package kata.mastermind

import org.scalatest._


class KeyUtilsTest extends FlatSpec {

  "dropExactMatches" should "return the original input when there are no perfect matches" in {
    val guess = Vector(1, 2, 3, 4)
    val secret = Vector(2, 3 ,4, 1)
    val result = KeyUtils.dropExactMatches(guess, secret)
    assertResult(guess) { result._1 }
    assertResult(secret) { result._2 }
  }

  "dropExactMatches" should "return input minus perfect matches" in {
    val guess = Vector(1, 2, 3, 4)
    val secret = Vector(1, 2 ,4, 1)
    val result = KeyUtils.dropExactMatches(guess, secret)
    assertResult(Vector(3, 4)) { result._1 }
    assertResult(Vector(4, 1)) { result._2 }
  }

  "countFuzzyMatches" should "return matches in single occurence A" in {
    val guess = Vector(1, 2, 3, 4)
    val secret = Vector(2, 3 ,4, 1)
    assertResult(4) { KeyUtils.countFuzzyMatches(guess, secret) }
  }

  "countFuzzyMatches" should "return matches in single occurence B" in {
    val guess = Vector(1, 2, 3, 6)
    val secret = Vector(2, 3 ,3, 1)
    assertResult(3) { KeyUtils.countFuzzyMatches(guess, secret) }
  }

  "countFuzzyMatches" should "return matches with mutliple occurences" in {
    val guess = Vector(3, 2, 6, 3)
    val secret = Vector(1, 3 ,3, 6)
    assertResult(3) { KeyUtils.countFuzzyMatches(guess, secret) }
  }

  "blackWhiteCount" should "match Plos One example R B G Y | R O O B" in {
    val guess = Vector(1, 2, 3, 4)
    val secret = Vector(1, 0, 0, 2)
    assertResult(List(1, 1)) { KeyUtils.blackWhiteCount(guess, secret) }
  }

  "blackWhiteCount" should "match Plos One example P O G Y | R O O B" in {
    val guess = Vector(5, 0, 3, 4)
    val secret = Vector(1, 0, 0, 2)
    assertResult(List(1, 0)) { KeyUtils.blackWhiteCount(guess, secret) }
  }

  "blackWhiteCount" should "match Plos One example R O B 0 | R O O B" in {
    val guess = Vector(1, 0, 2, 0)
    val secret = Vector(1, 0, 0, 2)
    assertResult(List(2, 2)) { KeyUtils.blackWhiteCount(guess, secret) }
  }

  "blackWhiteCount" should "find exact match" in {
    val guess = Vector(1, 0, 0, 2)
    val secret = Vector(1, 0, 0, 2)
    assertResult(List(4, 0)) { KeyUtils.blackWhiteCount(guess, secret) }
  }
}
