package com.mygdx.game.graph

import com.badlogic.gdx.ai.pfa.Heuristic

class NodeHeuristic extends Heuristic [Node]{

  override def estimate(node1: Node, node2: Node): Float = {
    node1.position.dst(node2.position)
  }

}
