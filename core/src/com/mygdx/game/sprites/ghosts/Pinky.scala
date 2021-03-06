package com.mygdx.game.sprites.ghosts

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Game
import com.mygdx.game.constants.Constants
import com.mygdx.game.sprites.pacman.{Down, Ghost, Left, Right, Still, Up}
import com.mygdx.game.textures.TextureLoader

class Pinky(game: Game) extends Ghost(game, new Vector2(game.level.ghostHousePos).sub( - Constants.TileSize, 0)) {

  val scatterTarget = game.level.tiles(27)(4).position

  override def update(delta: Float): Unit = {
    super.update(delta)
    if(!allowedOutHouse) {
      allowedOutHouse = true
    }
  }

  override def getTarget(): Vector2 = {
    if(inHouse){
      new Vector2(Constants.TileSize * 24, Constants.TileSize * 40)
    }else {
      mode match {
        case Scatter => scatterTarget
        case Chase => {
          val pacmansTile = game.level.getTile(new Vector2(game.pacman.position))
          game.pacman.currentDirection match {
            case Up => new Vector2(pacmansTile.position).add(0, Constants.TileSize * 4)
            case Down => new Vector2(pacmansTile.position).add(0, -Constants.TileSize * 4)
            case Left => new Vector2(pacmansTile.position).add(-Constants.TileSize * 4, 0)
            case Right => new Vector2(pacmansTile.position).add(Constants.TileSize * 4, 0)
            case Still => new Vector2(pacmansTile.position)
          }
        }
        case Frightened => new Vector2(game.pacman.position)
      }
    }
  }

  override lazy val textureRegion: TextureRegion = TextureLoader.Pinky
}
