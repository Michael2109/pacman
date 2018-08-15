package com.mygdx.game.sprites.ghosts

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Game
import com.mygdx.game.constants.Constants
import com.mygdx.game.sprites.pacman.Ghost
import com.mygdx.game.textures.TextureLoader

class Blinky(game: Game) extends Ghost(game, new Vector2(game.level.ghostHousePos).sub(-Constants.TileSize * 2, 0)) {

  val scatterTarget = game.level.tiles(29)(24).position

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
        case Chase => new Vector2(game.pacman.position)
        case Frightened => new Vector2(game.pacman.position)
      }
    }
  }

  override lazy val textureRegion: TextureRegion = TextureLoader.Blinky
}
