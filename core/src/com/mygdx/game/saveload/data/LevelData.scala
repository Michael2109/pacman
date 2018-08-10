package com.mygdx.game.saveload.data

import com.fasterxml.jackson.annotation.{JsonIgnore, JsonIgnoreProperties}

@JsonIgnoreProperties(Array("tilesInit"))
case class LevelData(tilesInit: List[List[TileData]]) {

 val tiles = tilesInit

}
