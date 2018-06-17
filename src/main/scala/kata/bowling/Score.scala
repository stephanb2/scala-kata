package kata.bowling

import scala.annotation.tailrec


object ScoreUtils {
  val N_FRAMES = 10

  def getBaseScore(game: List[Frame]): List[Int] = {
    game.take(N_FRAMES).map(_.knock.sum)
  }

  @tailrec
  def getBonus(game: List[Frame], result: List[Int] = List()): List[Int] = {
    game match {
      case Nil => result
      case (x: SpareFrame) :: xs => getBonus(xs, result :+ xs.head.knock.head)
      case (x: StrikeFrame) :: xs => getBonus(xs, result :+ xs.head.knock.sum)
      case _ => getBonus(game.tail, result :+ 0)
    }
  }

  def getScore(game: List[Frame]): List[Int] = {
    (getBaseScore(game), getBonus(game)).zipped.map(_ + _)
  }


  def getTotals(game: List[Frame]): List[Int] = {
    val score = (getBaseScore(game), getBonus(game)).zipped.map(_ + _)

    @tailrec
    def scoreAccum(score: List[Int], result: List[Int] = List()): List[Int] = {
      (score, result) match {
        case (Nil, _) => result
        case (_, Nil) => scoreAccum(score.tail, List(score.head))
        case _ => scoreAccum(score.tail, result :+ (score.head + result.last))
      }
    }
    scoreAccum(score)
  }
}