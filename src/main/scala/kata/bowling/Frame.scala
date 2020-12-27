package kata.bowling


abstract class Frame {
  val TOTAL_PINS = 10
  def knock: List[Int]
}

case class NormalFrame(down1: Int, down2: Int) extends  Frame {
  val knock = List(down1, down2)
  assert(down1 + down2 < TOTAL_PINS, "this is a spare or a strike")
}

case class SpareFrame(down: Int) extends Frame {
  val knock = List(down, TOTAL_PINS - down)
}

case class StrikeFrame() extends Frame {
  val knock = List(TOTAL_PINS, 0)
}
