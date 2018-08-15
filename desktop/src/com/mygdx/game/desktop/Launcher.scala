package com.mygdx.game.desktop

import java.awt.Toolkit

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.mygdx.game.Game
import com.mygdx.game.constants.Constants

object Launcher {
  def main(arg: Array[String]): Unit = {
    val config = new LwjglApplicationConfiguration
    config.width = (224 * Constants.FrameScale).asInstanceOf[Int]
    config.height = (280 * Constants.FrameScale).asInstanceOf[Int]
    config.title = Constants.Title
    config.fullscreen = false
    new LwjglApplication(new Game, config)
  }
}
