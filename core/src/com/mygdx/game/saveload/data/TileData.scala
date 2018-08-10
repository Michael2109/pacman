package com.mygdx.game.saveload.data

import com.fasterxml.jackson.annotation.{JsonIgnore, JsonIgnoreProperties}

@JsonIgnoreProperties(Array("tileTypeInit", "buildingIdInit", "doorConnectorInit"))
case class TileData(tileTypeInit: Int, buildingIdInit: Int, doorConnectorInit: Boolean) {

  val tileType = tileTypeInit

  val buildingId = buildingIdInit

  val doorConnector = doorConnectorInit

}
