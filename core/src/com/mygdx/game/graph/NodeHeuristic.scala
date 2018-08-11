package com.mygdx.game.graph

import com.badlogic.gdx.ai.pfa.Heuristic

class NodeHeuristic extends Heuristic [Node]{

  override def estimate(node1: Node, node2: Node): Float = 1
}
