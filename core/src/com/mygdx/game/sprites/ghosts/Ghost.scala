package com.mygdx.game.sprites.pacman

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.{Polygon, Rectangle, Vector2}
import com.mygdx.game.{Game, Score}
import com.mygdx.game.constants.Constants
import com.mygdx.game.sprites.GameSprite
import com.mygdx.game.sprites.ghosts.{Chase, GhostMode, Scatter}
import com.mygdx.game.sprites.level.{Door, Wall}
import com.mygdx.game.textures.TextureLoader
import com.mygdx.game.utils.Utils

import scala.collection.immutable.ListMap
import scala.util.Random

abstract class Ghost(game: Game, positionInit: Vector2) extends GameSprite(game, positionInit) {

  var mode: GhostMode = Chase

  private var directionVector: Vector2 = new Vector2(-1, 0)

  private var currentDirection: Direction = Left

  val rectangle: Rectangle = new Rectangle(0, 0, Constants.TileSize, Constants.TileSize)

  val width = 14
  val height = 14

  def getTarget(): Vector2

  def getDirectionVector(newDirection: Direction): Vector2 = {
    newDirection match {
      case Up => new Vector2(0, 1)
      case Down => new Vector2(0, -1)
      case Left => new Vector2(-1, 0)
      case Right => new Vector2(1, 0)
      case Still => new Vector2(0, 0)
    }
  }

  def checkNoFutureCollision(movement: Vector2): Boolean = {

    var collides = false
    position.add(movement)
    rectangle.setPosition(position.x - rectangle.width / 2, position.y - rectangle.height / 2)
    sprite.setPosition(rectangle.x, rectangle.y)
    game.level.tiles.flatten.filter(tile => tile.tileType == Wall).foreach(wall => {
      if (rectangle.overlaps(wall.sprite.getBoundingRectangle)) {
        collides = true
      }
    })
    position.sub(movement)

    !collides
  }

  override def update(delta: Float): Unit = {

    val target = getTarget()

    val targetTile = game.level.getTile(target).position

    // If travelling left check to see if can move up, down or left
    val canMove = Map(
      Up -> (checkNoFutureCollision(getDirectionVector(Up)), position.dst(new Vector2(targetTile).add(0, -Constants.TileSize))),
      Down -> (checkNoFutureCollision(getDirectionVector(Down)), position.dst(new Vector2(targetTile).add(0, Constants.TileSize))),
      Left -> (checkNoFutureCollision(getDirectionVector(Left)), position.dst(new Vector2(targetTile).add(Constants.TileSize, 0))),
      Right -> (checkNoFutureCollision(getDirectionVector(Right)), position.dst(new Vector2(targetTile).add(-Constants.TileSize, 0)))
    ).filter(move => move._2._1).filterKeys(_ != getOppositeDirection(currentDirection))

    // Get the closest if moves to the next tile
    val sortedMoves = ListMap(canMove.toSeq.sortBy(_._2._2):_*)

    val nextDirection = sortedMoves.head._1
    directionVector = getDirectionVector(nextDirection)
    currentDirection = nextDirection

    position.add(directionVector)

    rectangle.setPosition(position.x - rectangle.width / 2, position.y - rectangle.height / 2)
    sprite.setPosition(rectangle.x + rectangle.width / 2 - width / 2, rectangle.y + rectangle.height / 2 - height / 2)

    collisionDetection()
  }

  def getOppositeDirection(dir: Direction): Direction ={
    dir match {
      case Up => Down
      case Down => Up
      case Left => Right
      case Right => Left
    }
  }

  def collisionDetection(): Unit = {

  }

  override lazy val polygon = new Polygon(
    Array[Float](
      0, 0,
      width, 0,
      width, height,
      0, height
    )
  )
}
