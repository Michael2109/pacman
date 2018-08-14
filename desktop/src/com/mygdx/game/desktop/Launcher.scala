package com.mygdx.game.desktop

import java.awt.Toolkit

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.mygdx.game.Game
import com.mygdx.game.constants.Constants

object Launcher {
  def main(arg: Array[String]): Unit = {
    val config = new LwjglApplicationConfiguration
    val dimension = Toolkit.getDefaultToolkit.getScreenSize
    config.width = dimension.height - 100
    config.height = dimension.height - 100
    config.title = Constants.Title
    config.fullscreen = false
    new LwjglApplication(new Game, config)
  }
}
