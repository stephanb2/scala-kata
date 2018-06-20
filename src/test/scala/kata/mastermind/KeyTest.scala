package kata.mastermind

import org.scalatest._


class KeyTest extends FlatSpec {

  "exactMatch" should "return zero on no perfect matches" in {
    val guess = List(1, 2, 3, 4)
    val secret = List(2, 3 ,4, 1)
    assert(Key.exactMatch(guess, secret) === 0)
  }

  "it" should "return the number of perfect matches" in {
    val guess = List(1, 2, 3, 4)
    val secret = List(1, 2 ,4, 1)
    assert(Key.exactMatch(guess, secret) === 2)
  }


}
