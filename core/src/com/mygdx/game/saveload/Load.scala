package com.mygdx.game.saveload

import java.nio.file.{Files, Paths}

import com.fasterxml.jackson.databind.{ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.mygdx.game.Game
import com.mygdx.game.saveload.data.{LevelData, TileData}

import scala.io.Source

object Load {

  def load(path: String): LevelData ={
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
    mapper.enable(SerializationFeature.INDENT_OUTPUT)

    val levelData = mapper.readValue(Source.fromFile("SAVE.json").mkString, classOf[LevelData])

    levelData
  }

}
