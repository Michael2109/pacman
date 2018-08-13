/*
 * Cobalt Programming Language Compiler
 * Copyright (C) 2017 Michael Haywood
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mygdx.game.desktop

import java.awt.Toolkit

import com.badlogic.gdx.Gdx
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
    new LwjglApplication(new Game, config)
  }
}
