package advent

import org.scalatest.FunSuite

class JigsawTest extends FunSuite {

  test("parsing input to binary matrix") {
    val input = "..#\n###\n#..\n"
    val expected = Seq("001", "011", "100", "110")
    assertResult(expected ++ expected.map(_.reverse)){Jigsaw.getEdgeStrings(input)}
  }

  test("input pasrser") {
    val tiles:Seq[Tile] = Jigsaw.parseInput("./data/aoc20_tiles_test01.txt")
    println(tiles)
  }

  test( "match edge with other tiles") {
    val tiles = Jigsaw.parseInput("./data/aoc20_tiles_test01.txt")
    val matches = Jigsaw.matchEdge("0011", tiles)

    assertResult( List(1001) ){Jigsaw.matchEdge("0011", tiles).map(_._1)}
    assertResult( List((1001, List(1, 6))) ){Jigsaw.matchEdge("0111", tiles)}
    assertResult( List(1002, 1003) ){Jigsaw.matchEdge("0101", tiles).map(_._1)}
  }

  test("find adjacent tiles + count matches") {
    val tiles = Jigsaw.parseInput("./data/aoc20_tiles_test01.txt")
    assertResult( List((1002,1), (1003,1)) ) {Jigsaw.getAdjacent(tiles.head, tiles)}
  }

  test("find center tile from day 20 example") {
    val tiles = Jigsaw.parseInput("./data/aoc20_example.txt")
    val (collisions, adj_matrix) = Jigsaw.adjacencyMatrix(tiles)

    assertResult( List() ) { collisions }
    assertResult( Set(1427) ) { adj_matrix.filter(_._2.length == 4).keys }
    assertResult( Set(1951, 3079, 2971, 1171) ) {adj_matrix.filter(_._2.length == 2).keys}
  }

  test( "solve individual puzzle") {
    val tiles = Jigsaw.parseInput("./data/aoc20_input01.txt")
    val (collisions, adj_matrix) = Jigsaw.adjacencyMatrix(tiles)
    val corners = adj_matrix.filter(_._2.length == 2).keys

    println(f"collisions: $collisions")
    println(f"center: ${adj_matrix.filter(_._2.length == 4).keys}")
    println(f"corners: $corners")
    // beware the product is a Long
    println(corners.foldLeft(1L)(_ * _))
  }
}
