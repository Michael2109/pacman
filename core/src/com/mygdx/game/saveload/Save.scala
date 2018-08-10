package com.mygdx.game.saveload

import java.nio.file.{Files, Paths}

import com.fasterxml.jackson.databind.{ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.mygdx.game.Game
import com.mygdx.game.constants.Constants
import com.mygdx.game.saveload.data.{LevelData, TileData}

import scala.collection.mutable.ListBuffer

object Save {

  def saveToXML(game: Game): Unit ={
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    mapper.enable(SerializationFeature.INDENT_OUTPUT)

    val tiles = ListBuffer[ListBuffer[TileData]]()

    val levelData = new LevelData(tiles.map(_.toList).toList)

    val xml = mapper.writeValueAsBytes(levelData)

    Files.write(Paths.get("SAVE.json"), xml)
  }

}
