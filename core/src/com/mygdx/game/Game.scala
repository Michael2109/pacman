package com.mygdx.game

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.math.{Vector2, Vector3}
import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.mygdx.game.constants.Constants
import com.mygdx.game.user.User
import com.mygdx.game.graph.Graph
import com.mygdx.game.sprites.ghosts._
import com.mygdx.game.sprites.level.Level
import com.mygdx.game.sprites.pacman.{Ghost, Pacman}

class Game extends ApplicationAdapter {

  lazy val camera: OrthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth, Gdx.graphics.getHeight)

  lazy val shapeRenderer = new ShapeRenderer()

  lazy val batch: PolygonSpriteBatch = new PolygonSpriteBatch()

  lazy val level: Level = new Level(this)

  lazy val pacman: Pacman = new Pacman(this, new Vector2(Constants.TileSize * 14, Constants.TileSize * 7 + Constants.TileSize / 2))

  lazy val blinky: Ghost = new Blinky(this, new Vector2(Constants.TileSize * 14, Constants.TileSize * 16 + Constants.TileSize / 2))

  lazy val pinky: Ghost = new Pinky(this, new Vector2(Constants.TileSize * 14, Constants.TileSize * 16 + Constants.TileSize / 2))

  lazy val inky: Ghost = new Inky(this, new Vector2(Constants.TileSize * 14, Constants.TileSize * 16 + Constants.TileSize / 2))

  lazy val clyde: Ghost = new Clyde(this, new Vector2(Constants.TileSize * 14, Constants.TileSize * 16 + Constants.TileSize / 2))

  lazy val ghosts: List[Ghost] = List(blinky, pinky, inky, clyde)

  var currentLevel = 1

  override def create(): Unit = {

    Gdx.input.setInputProcessor(new User(this))

    camera.setToOrtho(false)
    Gdx.graphics.setVSync(true)

    batch.enableBlending()

    val scale = level.height.asInstanceOf[Float] / Gdx.graphics.getWidth.asInstanceOf[Float]
    val width = (Gdx.graphics.getWidth * scale).asInstanceOf[Int]
    val height = (Gdx.graphics.getHeight * scale).asInstanceOf[Int]
    camera.setToOrtho(false, width, height)
    camera.position.set(new Vector3(level.width / 2, level.height / 2, 0))
  }

  def update(delta: Float): Unit = {

    camera.update()

    level.update(delta)

    pacman.update(delta)

    ghosts.foreach(_.update(delta))

    level.collectables.foreach(_.update(delta))
    level.collectables.foreach(_.collisionDetection())

    changeMode()
  }

  override def render(): Unit = {
    update(math.min(Gdx.graphics.getDeltaTime(), 1 / 60f))

    batch.setProjectionMatrix(camera.combined)
    shapeRenderer.setProjectionMatrix(camera.combined)

    Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth, Gdx.graphics.getHeight)
    Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (if (Gdx.graphics.getBufferFormat.coverageSampling) GL20.GL_COVERAGE_BUFFER_BIT_NV else 0))
    //   Gdx.gl20.glEnable(GL20.GL_TEXTURE_2D)
    //  Gdx.gl20.glEnable(GL20.GL_BLEND)
    Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)

    level.render()

    pacman.render()

    ghosts.foreach(_.render())

    level.collectables.foreach(_.render())
  }

  override def dispose(): Unit = {
    batch.dispose()
    shapeRenderer.dispose()
  }

  var before = System.currentTimeMillis()
  var currentMode: GhostMode = Scatter
  var scatterTime = 7000
  var chaseTime = 5000

  def changeMode(): Unit ={
    currentMode match {
      case Scatter => {
        if(System.currentTimeMillis() - before > scatterTime){
          currentMode = Chase
          ghosts.foreach(_.mode = currentMode)
          ghosts.foreach(ghost => ghost.currentDirection = ghost.getOppositeDirection(ghost.currentDirection))
          before = System.currentTimeMillis()
        }
      }
      case Chase => {
        if(System.currentTimeMillis() - before > chaseTime){
          currentMode = Scatter
          ghosts.foreach(_.mode = currentMode)
          ghosts.foreach(ghost => ghost.currentDirection = ghost.getOppositeDirection(ghost.currentDirection))
          before = System.currentTimeMillis()
        }
      }
      case Frightened =>
    }
  }
}
