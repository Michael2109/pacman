package com.mygdx.game.sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.{Polygon, Vector2}
import com.mygdx.game.Game
import com.mygdx.game.graph.Graph
import com.mygdx.game.textures.TextureLoader

class Dot(game: Game, positionInit: Vector2) extends GameSprite(game, positionInit) {

  var collected = false

  val width = 1
  val height = 1

  override def update(delta: Float): Unit = {
    sprite.setPosition(position.x - width / 2, position.y - height / 2)
  }

  override def render(): Unit = {
    if(!collected) {
      super.render()
    }
  }

  override lazy val polygon = new Polygon(
    Array[Float](
      0, 0,
      width, 0,
      width, height,
      0, height
    )
  )

  override lazy val textureRegion: TextureRegion = TextureLoader.Dot
}
