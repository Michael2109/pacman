package com.mygdx.game.sprites.level

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.{Polygon, Vector2}
import com.mygdx.game.Game
import com.mygdx.game.constants.Constants
import com.mygdx.game.graph.Graph
import com.mygdx.game.sprites.{Collectable, Dot, Fruit, GameSprite}
import com.mygdx.game.textures.TextureLoader

import scala.collection.mutable.ListBuffer

class Level(game: Game) extends GameSprite(game, new Vector2(0, 0)) {

  val ghostHousePos = new Vector2(Constants.TileSize * 13, Constants.TileSize * 16 + Constants.TileSize / 2)

  val width = 224
  val height = 248

  val tileWidth = Constants.TileSize
  val tileHeight = Constants.TileSize

  /**
    * 0 == Tile
    * 1 == Empty
    */
  val tileNumbers = List(
    List(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0,0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
    List(0,0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
    List(0,0, 0, 4, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 4, 0, 0, 0),
    List(0,0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
    List(0,0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
    List(0,0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
    List(0,0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
    List(0,0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0),

    List(0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 3, 3, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 3, 3, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    List(2,2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 0, 0, 2, 2, 2, 2, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2),
    List(0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0,0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),

    List(0,0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
    List(0,0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
    List(0,0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0),
    List(0,0, 0, 4, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 4, 0, 0, 0),
    List(0,0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0),
    List(0,0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0),
    List(0,0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0),
    List(0,0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
    List(0,0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0),
    List(0,0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0),
    List(0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    List(0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
  ).reverse

  val tiles: List[List[Tile]] = {
    val result = ListBuffer[ListBuffer[Tile]]()
    tileNumbers.zipWithIndex.foreach {
      case (tileRow, rowIndex) => {
        val row = ListBuffer[Tile]()
        tileRow.zipWithIndex.foreach {
          case (tile, columnIndex) =>
            val tileType = tile match {
              case 0 => Wall
              case 1 => Dot
              case 2 => Empty
              case 3 => Door
              case 4 => Fruit
            }
            row += new Tile(game, columnIndex, rowIndex, tileType)
        }
        result += row
      }
    }
    result.map(_.toList).toList
  }

  lazy val collectables: List[Collectable] = {
    val collectables = ListBuffer[Collectable]()
    tiles.foreach(row => {
      row.foreach(tile => {
        tile.tileType match {
          case Dot => collectables += new Dot(game, new Vector2(tile.position.x, tile.position.y))
          case Fruit => collectables += new Fruit(game, new Vector2(tile.position.x, tile.position.y))
          case _ =>
        }
      })

    })
    collectables.toList
  }

  def getTile(pos: Vector2): Tile = tiles.flatten.sortBy(_.position.dst(pos)).head

  override def update(delta: Float): Unit = {
    sprite.setPosition(position.x, position.y)
    tiles.foreach(row => row.foreach(_.update(delta)))
  }

  override def render(): Unit = {
    super.render()
    if(Constants.DisplayWalls) {
      tiles.foreach(row => row.foreach(_.render()))
    }
  }

  override lazy val polygon = new Polygon(
    Array[Float](
      0, 0,
      width, 0,
      width, height,
      0, height
    )
  )

  override lazy val textureRegion: TextureRegion = TextureLoader.Level
}
