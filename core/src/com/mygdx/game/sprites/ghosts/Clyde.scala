package com.mygdx.game.sprites.ghosts

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Game
import com.mygdx.game.constants.Constants
import com.mygdx.game.sprites.pacman.{Down, Ghost, Left, Right, Still, Up}
import com.mygdx.game.textures.TextureLoader

class Clyde(game: Game, positionInit: Vector2) extends Ghost(game, positionInit) {

  val scatterTarget = game.level.tiles(4)(20).position

  override def getTarget(): Vector2 = {
    mode match {
      case Scatter => scatterTarget
      case Chase => {
        val pacmansTile = game.level.getTile(new Vector2(game.pacman.position))

        // Get the distance to pacmans tile
        val distance = pacmansTile.position.dst(game.level.getTile(position).position)

        if (distance / Constants.TileSize < 8) {
          // If it is less than 8 target the scatter target
          scatterTarget
        }
        else {
          // if it is more than 8 target pacman
          new Vector2(pacmansTile.position)
        }
      }
      case Frightened => new Vector2(game.pacman.position)
    }
  }

  override lazy val textureRegion: TextureRegion = TextureLoader.Clyde

}
