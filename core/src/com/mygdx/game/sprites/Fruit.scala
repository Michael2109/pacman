package com.mygdx.game.sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.{Polygon, Vector2}
import com.mygdx.game.{Game, Score}
import com.mygdx.game.textures.TextureLoader

class Fruit(game: Game, positionInit: Vector2) extends Collectable(game, positionInit) {

  val width = 4
  val height = 4

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

  override lazy val textureRegion: TextureRegion = TextureLoader.Fruit

  override def collisionDetection(): Unit ={
    if(sprite.getBoundingRectangle.overlaps(game.pacman.sprite.getBoundingRectangle) && !collected){
      collected = true
      Score.TotalScore += 100
    }
  }
}
