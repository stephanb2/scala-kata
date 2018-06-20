package kata.mastermind

object Key{

  def exactMatch(guess:List[Int], secret:List[Int], result:Int = 0): Int = {
    guess match {
      case Nil => result
      case x :: xs => {
        if (x == secret.head)
          exactMatch(xs, secret.tail, result + 1)
        else
          exactMatch(xs, secret.tail, result)
      }
    }
  }

  def dropExactMatches(guess:List[Int], secret:List[Int]): Tuple2[List[Int], List[Int]] = {
    guess match {
      case Nil => Tuple2(guess, secret)
      case _ => {
        if (guess.head == secret.head)
          dropExactMatches(guess.tail, secret.tail)
        else
          dropExactMatches(guess, secret)
      }
    }
  }

}
