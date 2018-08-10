package com.mygdx.game.utils

import com.badlogic.gdx.math.{EarClippingTriangulator, Polygon}
import com.badlogic.gdx.utils.ShortArray

/**
  * Triangulates polygons to allow for drawing shapes
  */
object Triangulator {

  def triangulate(polygon: Polygon): Array[Short] = {
    var pointsCoords = new ShortArray();
    val triangulator = new EarClippingTriangulator();

    // Cut in triangles
    pointsCoords = triangulator.computeTriangles(polygon.getVertices())
    pointsCoords.toArray
  }

}
