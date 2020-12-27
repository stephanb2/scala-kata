
val start_ts = 939
val periods_input = "7,13,x,x,59,x,31,19"

val periods = periods_input.split(",")
  .filter(str => str != "x")
  .map(s => s.toInt)

val phase_at_ts = periods.map(x => start_ts % x)
val next_zero = periods.zip(phase_at_ts).map(x => x._1 - x._2)
val next_id = next_zero.zipWithIndex.min._2
val response = periods(next_id) * next_zero.min

def codeToNext(start_ts: Int, periods: Seq[Int]) ={
  val phase_at_ts = periods.map(x => start_ts % x)
  val next_zero = periods.zip(phase_at_ts).map(x => x._1 - x._2)
  val next_id = next_zero.zipWithIndex.min._2
  periods(next_id) * next_zero.min
}

// test
codeToNext(start_ts, periods)


val start02 = 1000001
val periods_in02 = "29,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,577,x,x,x,x,x,x,x,x,x,x,x,x,13,17,x,x,x,x,19,x,x,x,23,x,x,x,x,x,x,x,601,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,37"

val periods02 = periods_in02.split(",")
  .filter(str => str != "x")
  .map(s => s.toInt)

codeToNext(start02, periods02)