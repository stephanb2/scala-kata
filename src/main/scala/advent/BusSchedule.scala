package advent

import scala.annotation.tailrec

// cf. https://adventofcode.com/2020/day/13#part2
case class Bus(period: Int, phase:Int)

object BusSchedule {
  def schedule_from_string(schedule_input: String): Seq[Bus] = schedule_input
    .split(",").zipWithIndex
    .filter(x => x._1 != "x")
    .map(x => Bus(x._1.toInt, x._2 % x._1.toInt))

  // utility function to compute inverse modulo period (convert time to next bus to expected phase % period)
  def inverse_phase(schedule: Seq[Bus]) : Seq[Bus] = {
    schedule.map(x => if (x.phase >0) Bus(x.period, x.period - x.phase) else x)
  }

  @tailrec
  def find_match_product(start_ts: Long, inc: Long, schedule: Seq[Bus], limit: Long): Long = {
    if (limit < 0) -1
    else schedule match {
      case Seq() => start_ts
      case sched if (start_ts % sched.head.period == sched.head.phase) =>
        find_match_product(start_ts, inc*schedule.head.period, schedule.tail, limit-1)
      case _ =>
        find_match_product(start_ts+inc, inc, schedule, limit-1)
    }
  }

  def find_match(schedule: Seq[Bus]): Long = {
    val limit = schedule.map(_.period).sum
    find_match_product(0L, schedule.head.period, inverse_phase(schedule.tail), limit)
  }
}

object AdventDay13 extends App {
  override def main(args: Array[String]): Unit = {
    val puzzle_string = "23,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,509,x,x,x,x,x,x,x,x,x,x,x,x,13,17,x,x,x,x,x,x,x,x,x,x,x,x,x,x,29,x,401,x,x,x,x,x,37,x,x,x,x,x,x,x,x,x,x,x,x,19"
    val puzzle = BusSchedule.schedule_from_string(puzzle_string)
    println(BusSchedule.find_match(puzzle))
  }
}

