package com.mygdx.game.user

import com.badlogic.gdx.math.{Vector2, Vector3}
import com.badlogic.gdx.{Gdx, Input, InputProcessor}
import com.mygdx.game.Game
import com.mygdx.game.sprites.pacman.{Down, Up, Left, Right}

class User(game: Game) extends InputProcessor {

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

  override def keyUp(keycode: Int): Boolean = false

  override def scrolled(amount: Int): Boolean = {
    game.camera.zoom = game.camera.zoom + amount / 3f
    false
  }

  override def keyTyped(character: Char): Boolean = false

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false

  override def keyDown(keycode: Int): Boolean = {/*
    keycode match {
      case Input.Keys.ESCAPE => System.exit(0)
      case Input.Keys.UP | Input.Keys.W  => game.pacman.changeDirection(Up())
      case Input.Keys.DOWN | Input.Keys.S => game.pacman.changeDirection(Down())
      case Input.Keys.LEFT | Input.Keys.A => game.pacman.changeDirection(Left())
      case Input.Keys.RIGHT | Input.Keys.D => game.pacman.changeDirection(Right())
    }*/
    false
  }

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = false

  private def unprojectedMousePosition() = {
    val mousePos3 = game.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0))
    new Vector2(mousePos3.x, mousePos3.y)
  }
}
