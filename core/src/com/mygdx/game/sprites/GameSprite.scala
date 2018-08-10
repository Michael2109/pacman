package com.mygdx.game.sprites

import com.badlogic.gdx.graphics.g2d.{PolygonRegion, PolygonSprite, TextureRegion}
import com.badlogic.gdx.math.{Polygon, Vector2}
import com.mygdx.game.Game
import com.mygdx.game.utils.Triangulator

abstract class GameSprite(game: Game, positionInit: Vector2) {

  val position = positionInit

  val textureRegion: TextureRegion

  /** The shape of the physics object and sprite */
  val polygon: Polygon

  /** The polygon image */
  lazy val sprite: PolygonSprite = new PolygonSprite(new PolygonRegion(textureRegion, polygon.asInstanceOf[Polygon].getVertices, Triangulator.triangulate(polygon.asInstanceOf[Polygon])))

  /**
    * Performs game logic
    *
    * @param delta
    */
  def update(delta: Float): Unit

  /**
    * Renders every game loop
    */
  def render(): Unit = {
    game.batch.begin()
    sprite.draw(game.batch)
    game.batch.end()
  }

  /**
    * Renders after the first render.
    * Used for text drawn on top of the first render.
    */
  def renderLast(): Unit = ()

  /**
    * Called when the object is being destroyed
    */
  def onDestroy(): Unit = ()


}
