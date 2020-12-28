package advent

import org.scalatest.FunSuite


// cf. https://adventofcode.com/2020/day/13#part2
class BusScheduleTest extends FunSuite {

  test("read schedule from string description"){
    val input = "7,13,x,x,59"
    val expected = Seq(Bus(7,0), Bus(13,1), Bus(59,4))

    assertResult(expected) {BusSchedule.schedule_from_string(input)}
  }

  test("find next solution using product of periods"){
    val schedule_01 = BusSchedule.schedule_from_string("17,x,13,19")
    val sched_inverse = BusSchedule.inverse_phase(schedule_01)
    val find_01 = BusSchedule.find_match_product(0L, 17, sched_inverse.tail, 19*13)

    val expected_inverse = Seq(Bus(17,0), Bus(13, 11), Bus(19, 16))
    assertResult(expected_inverse) {sched_inverse}
    assertResult(3417) {find_01}
  }

  test( "test product matcher with examples for Advent of Code"){
    val schedule_string = Seq("67,7,59,61", "67,x,7,59,61", "67,7,x,59,61", "1789,37,47,1889")
    val inputs = schedule_string.map(BusSchedule.schedule_from_string)

    val expected = Seq(754018L, 779210L, 1261476L, 1202161486L)
    assertResult(expected) {inputs.map(BusSchedule.find_match)}
  }

}
