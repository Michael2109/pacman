package com.mygdx.game.sprites.ghosts

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Game
import com.mygdx.game.sprites.pacman.Ghost
import com.mygdx.game.textures.TextureLoader

class Blinky(game: Game, positionInit: Vector2) extends Ghost(game, positionInit) {

  val scatterTarget = game.level.tiles(27)(24).position

  override def getTarget(): Vector2 = {
    mode match {
      case Scatter => scatterTarget
      case Chase => new Vector2(game.pacman.position)
      case Frightened => new Vector2(game.pacman.position)
    }
  }

  override lazy val textureRegion: TextureRegion = TextureLoader.Blinky

}
