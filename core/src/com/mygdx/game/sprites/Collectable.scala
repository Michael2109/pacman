package com.mygdx.game.sprites

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.{Game, Score}

abstract class Collectable(game: Game, positionInit: Vector2) extends GameSprite(game, positionInit) {

  var collected = false

  def collisionDetection(): Unit
}
