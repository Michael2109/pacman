package com.mygdx.game.utils

object Utils {

  def roundTo(number: Float, nearest: Int): Int ={
    nearest * (math.round(number / nearest.asInstanceOf[Float]))
  }

}
