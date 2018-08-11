package com.mygdx.game.textures

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.{TextureAtlas, TextureRegion}
import com.badlogic.gdx.tools.texturepacker.TexturePacker
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings

/**
  * Allows for simple loading of textures
  * Methods are used instead of values storing the textureRegion.
  * This is done so that the atlas textureRegion stays bound and the textureRegion drawn is retrieved from it.
  */
object TextureLoader {

  // todo organise these into sections and comment them all

  /** The settings for the texture packer */
  private val Settings = new Settings
  Settings.pot = false
  Settings.maxWidth = 1024
  Settings.maxHeight = 1024
  Settings.edgePadding = false


  /** Processes all images creating combined images */
  TexturePacker.process(Settings, "images", "packed_images", "packed_textures")


  /** Allows for selecting a region from within the packed images */
  val atlas = new TextureAtlas(Gdx.files.internal("packed_images/packed_textures.atlas"))

  /**
    * Level
    */
  val Level: TextureRegion = atlas.findRegion("level")

  /**
    * Dot
    */
  val Dot: TextureRegion = atlas.findRegion("dot")

  val Wall: TextureRegion = atlas.findRegion("wall")
  val Door: TextureRegion = atlas.findRegion("door")
  val Intersection: TextureRegion = atlas.findRegion("intersection")
  val Empty: TextureRegion = atlas.findRegion("empty")

  val Pacman: TextureRegion = atlas.findRegion("pacman")

  val Blinky: TextureRegion = atlas.findRegion("ghosts/blinky")
  val Pinky: TextureRegion = atlas.findRegion("ghosts/pinky")
  val Inky: TextureRegion = atlas.findRegion("ghosts/inky")
  val Clyde: TextureRegion = atlas.findRegion("ghosts/clyde")

}
