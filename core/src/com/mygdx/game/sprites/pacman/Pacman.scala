package com.mygdx.game.sprites.pacman

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.{Polygon, Rectangle, Vector2}
import com.mygdx.game.{Game, Score}
import com.mygdx.game.constants.Constants
import com.mygdx.game.sprites.GameSprite
import com.mygdx.game.sprites.level.{Door, Wall}
import com.mygdx.game.textures.TextureLoader
import com.mygdx.game.utils.Utils

class Pacman(game: Game, positionInit: Vector2) extends GameSprite(game, positionInit) {

  var currentDirection: Direction = Left
  private var directionVector: Vector2 = new Vector2(-1, 0)

  val rectangle: Rectangle = new Rectangle(0, 0, Constants.TileSize, Constants.TileSize)

  val width = 15
  val height = 15

  def getDirectionVector(newDirection: Direction): Vector2 ={
    newDirection match {
      case Up => new Vector2(0, 1)
      case Down => new Vector2(0, -1)
      case Left => new Vector2(-1, 0)
      case Right => new Vector2(1, 0)
      case Still => new Vector2(0, 0)
    }
  }

  def checkNoFutureCollision(movement: Vector2): Boolean ={

    var collides = false
    position.add(movement)
    rectangle.setPosition(position.x - rectangle.width / 2, position.y - rectangle.height / 2)
    sprite.setPosition(rectangle.x, rectangle.y)
    game.level.tiles.flatten.filter(tile => tile.tileType == Wall || tile.tileType == Door).foreach(wall => {
      if(rectangle.overlaps(wall.sprite.getBoundingRectangle)){
        collides = true
      }
    })
    position.sub(movement)

    !collides
  }

  override def update(delta: Float): Unit = {

    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      val nextDirectionVector = game.pacman.getDirectionVector(Up)
      if (checkNoFutureCollision(nextDirectionVector)) {
        directionVector = nextDirectionVector
        currentDirection = Up
      }
    }
    if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      val nextDirectionVector = game.pacman.getDirectionVector(Down)
      if (checkNoFutureCollision(nextDirectionVector)) {
        directionVector = nextDirectionVector
        currentDirection = Down
      }
    }
    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      val nextDirectionVector = game.pacman.getDirectionVector(Left)
      if (checkNoFutureCollision(nextDirectionVector)) {
        directionVector = nextDirectionVector
        currentDirection = Left
      }
    }
    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      val nextDirectionVector = game.pacman.getDirectionVector(Right)
      if (checkNoFutureCollision(nextDirectionVector)) {
        directionVector = nextDirectionVector
        currentDirection = Right
      }
    }

    position.add(directionVector)

    rectangle.setPosition(position.x - rectangle.width / 2, position.y - rectangle.height / 2)
    sprite.setPosition(rectangle.x + rectangle.width / 2 - width / 2, rectangle.y + rectangle.height / 2 - height / 2)

    collisionDetection()
  }

  def collisionDetection(): Unit ={
    game.level.tiles.flatten.filter(tile => tile.tileType == Wall || tile.tileType == Door).foreach(wall => {
      if(rectangle.overlaps(wall.sprite.getBoundingRectangle)){
        position.set(Utils.roundTo(position.x + Constants.TileSize / 2, Constants.TileSize) - Constants.TileSize / 2, Utils.roundTo(position.y + Constants.TileSize / 2, Constants.TileSize) - Constants.TileSize / 2)
        directionVector = getDirectionVector(Still)
        rectangle.setPosition(position.x - rectangle.width / 2, position.y - rectangle.height / 2)
        currentDirection = Still
      }
    })

    if(position.x < -Constants.TileSize * 2){
      position.x = Constants.TileSize * 29
    }

    if(position.x > Constants.TileSize * 29){
      position.x = -Constants.TileSize * 1
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

  override lazy val textureRegion: TextureRegion = TextureLoader.Pacman
}
