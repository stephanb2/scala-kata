package kata.bowling

import scala.collection.mutable


object ScoreUtils {
  val N_FRAMES = 10

  def getBaseScore(game: List[Frame]): List[Int] = {
    game.take(N_FRAMES)
      .map(f => f.knock._1 + f.knock._2)
  }


  def getBonus(game: List[Frame]): List[Int] = {
    var result = mutable.MutableList[Int]()

    for (i <- 0 to game.length - 2) {
      val bonus = game(i) match {
        case n: NormalFrame => 0
        case s: SpareFrame => game(i + 1).knock._1
        case s: StrikeFrame => game(i + 1).knock._1 + game(i + 1).knock._2
      }
      result += bonus
    }
    result += 0 //pad last frame to simplify score addition
    result.take(N_FRAMES).toList
  }


  def getScore(game: List[Frame]): List[Int] = {
    (getBaseScore(game), getBonus(game)).zipped.map(_ + _)
  }

  def getTotals(game: List[Frame]): List[Int] = {
    val score = (getBaseScore(game), getBonus(game)).zipped.map(_ + _)
    var accum = 0
    for (x <- score) yield {
      accum += x
      accum
    }
  }
}