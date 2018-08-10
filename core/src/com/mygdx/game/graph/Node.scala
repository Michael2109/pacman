package com.mygdx.game.graph

import com.badlogic.gdx.ai.pfa.Connection
import com.badlogic.gdx.math.Vector2

object NodeCounter {
  private var nextId = 0

  def getNextId(): Int ={
    val currentNodeCount = nextId.intValue()
    nextId = nextId + 1
    currentNodeCount
  }
}

class Node(idInit: Int, buildingIdInit: Int, positionInit: Vector2) {

  val id = idInit

  val buildingId = buildingIdInit

  val position = positionInit

  val connections: com.badlogic.gdx.utils.Array[Connection[Node]] = new com.badlogic.gdx.utils.Array[Connection[Node]]()

  override def toString: String = id + " " + position
}
