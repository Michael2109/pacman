package com.mygdx.game.sprites.ghosts

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Game
import com.mygdx.game.constants.Constants
import com.mygdx.game.sprites.pacman.{Down, Ghost, Left, Right, Still, Up}
import com.mygdx.game.textures.TextureLoader

class Inky(game: Game, positionInit: Vector2) extends Ghost(game, positionInit) {

  val scatterTarget = game.level.tiles(4)(20).position

  override def getTarget(): Vector2 = {
    if(inHouse){
      new Vector2(Constants.TileSize * 24, Constants.TileSize * 40)
    }else {
      mode match {
        case Scatter => scatterTarget
        case Chase => {
          val pacmansTile = game.level.getTile(new Vector2(game.pacman.position))
          val futurePacmanPos = game.pacman.currentDirection match {
            case Up => new Vector2(pacmansTile.position).add(0, Constants.TileSize * 2)
            case Down => new Vector2(pacmansTile.position).add(0, -Constants.TileSize * 2)
            case Left => new Vector2(pacmansTile.position).add(-Constants.TileSize * 2, 0)
            case Right => new Vector2(pacmansTile.position).add(Constants.TileSize * 2, 0)
            case Still => new Vector2(pacmansTile.position)
          }
          val blinkyPos = new Vector2(game.level.getTile(game.blinky.position).position)

          val blinkyToPacman = futurePacmanPos.sub(blinkyPos)

          blinkyPos.add(blinkyToPacman).add(blinkyToPacman)
        }
        case Frightened => new Vector2(game.pacman.position)
      }
    }
  }


  override lazy val textureRegion: TextureRegion = TextureLoader.Inky
}
