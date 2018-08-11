package com.mygdx.game.sprites.level

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.{Polygon, Vector2}
import com.mygdx.game.Game
import com.mygdx.game.constants.Constants
import com.mygdx.game.sprites.GameSprite
import com.mygdx.game.textures.TextureLoader

class Tile(game: Game, positionInit: Vector2, tileTypeInit: TileType) extends GameSprite(game, positionInit) {

  val tileType = tileTypeInit

  val width = Constants.TileSize
  val height = Constants.TileSize

  override def update(delta: Float): Unit = {
    sprite.setPosition(position.x - width / 2, position.y - height / 2)
  }

  override lazy val polygon = new Polygon(
    Array[Float](
      0, 0,
      width, 0,
      width, height,
      0, height
    )
  )

  override lazy val textureRegion: TextureRegion = {
    tileType match {
      case Door => TextureLoader.Door
      case Wall => TextureLoader.Wall
      case Dot => TextureLoader.Empty
      case Empty => TextureLoader.Empty
    }
  }
}
