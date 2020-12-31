package advent

import scala.collection.mutable.ListBuffer
import scala.io.Source

case class Tile(id:Int, edges:Seq[String])

object Jigsaw {

  def getEdgeStrings(input: String): Seq[String] = {
    val binstring = input.replace(".", "0")
      .replace("#", "1")
      .split("\n")

    val west = binstring.map(line => line.head.toString).reduce(_ + _)
    val east = binstring.map(line => line.reverse.head.toString).reduce(_ + _)

    val direct_edges = Seq(binstring.head, west, binstring.reverse.head, east)
    // return normal and flipped edges
    direct_edges ++ direct_edges.map(_.reverse)
  }

  def parseInput(file_path: String): Seq[Tile] = {
    val bufferedSource = Source.fromFile(file_path)
    var tile_string = new StringBuilder()
    var id: Int = -1
    val tiles = new ListBuffer[(Int, String)]()
    for (line <- bufferedSource.getLines) line match {
      case "" => tiles += Tuple2(id, tile_string.toString())
      case s if s.startsWith("Tile") => {
        tile_string = new StringBuilder()
        id = "[0-9]+".r.findFirstMatchIn(line).get.toString().toInt
      }
      case _ => tile_string ++= line + "\n"
    }
    bufferedSource.close

    tiles.map(t => Tile(t._1, getEdgeStrings(t._2)))
  }

  def matchEdge(edge: String, tiles: Seq[Tile]): Seq[(Int, Seq[Int])] = {
    tiles.map(t => t.id -> t.edges.zipWithIndex.filter(_._1 == edge).map(_._2))
      .filter(_._2 != List())
  }

  // list adjacent tiles and number of matching edges
  def getAdjacent(tile: Tile, other: Seq[Tile]): Seq[(Int, Int)] = {
    tile.edges.take(4).flatMap(matchEdge(_, other))
      .filter(_._1 != tile.id)
      .map(t => (t._1, t._2.length))
  }

  // get adjacency matrix and list of collisions
  def adjacencyMatrix(tiles: Seq[Tile]): (Seq[Int], Map[Int, Seq[Int]]) = {
    val adj_list = tiles.map(t => t.id -> Jigsaw.getAdjacent(t, tiles)).toMap
    val collisions = adj_list.filter(_._2.exists(_._2 > 1)).keys
    val adj_matrix = adj_list.transform((id, lst) => lst.map(_._1))
    (collisions.toList, adj_matrix)
  }

}
