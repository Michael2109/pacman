package com.mygdx.game.graph

import com.badlogic.gdx.ai.pfa.{Connection, DefaultGraphPath}
import com.badlogic.gdx.ai.pfa.indexed.{IndexedAStarPathFinder, IndexedGraph}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.{Batch, BitmapFont}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.constants.Constants

class Graph(nodesInit: List[Node]) extends IndexedGraph[Node] {

  val font = new BitmapFont

  val nodes = nodesInit

  private val pathFinder: IndexedAStarPathFinder[Node] = new IndexedAStarPathFinder(this)
  private val heuristic = new NodeHeuristic()

  override def getIndex(node: Node): Int = {
    node.id
  }

  override def getNodeCount(): Int = {
    nodes.size
  }

  override def getConnections(fromNode: Node): com.badlogic.gdx.utils.Array[Connection[Node]] = {
    fromNode.connections
  }

  def getNode(id: Int): Option[Node] ={
    nodes.find(_.id == id)
  }

  def getNode(position: Vector2): Node ={
    var minDistance: Float = Float.MaxValue
    var closestNode: Node = null
    for (node <- nodes) {
      val distance = position.dst(node.position)
      if (distance < minDistance) {
        minDistance = distance
        minDistance = distance
        closestNode = node
      }
    }

    closestNode
  }

  def search(startPosition: Vector2, endPosition: Vector2): DefaultGraphPath[Node] = {
    search(getNode(startPosition), getNode(endPosition))
  }

  def search(startNode: Node, endNode: Node): DefaultGraphPath[Node] = {

    val graphPath = new DefaultGraphPath[Node]()

    pathFinder.searchNodePath(startNode, endNode, heuristic, graphPath)

    graphPath
  }

  def draw(shapeRenderer: ShapeRenderer, batch : Batch): Unit = {
    for (node <- nodes) {

      shapeRenderer.setColor(Color.WHITE)

      for (connection <- node.connections.items) {
        if (connection != null) {
          shapeRenderer.begin(ShapeType.Line)
          shapeRenderer.line(node.position, connection.getToNode.position)
          shapeRenderer.end()
        }
      }
    }
  }
}
