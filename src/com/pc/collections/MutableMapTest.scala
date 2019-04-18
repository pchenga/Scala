package com.pc.collections

import scala.collection.mutable.Map

object MutableMapTest {
  
  def main(args:Array[String]):Unit = {
    
    val map = Map.empty[String,Int]
    
    map += ("One" -> 1)
    map += ("Two" -> 2)
    map += ("One" -> 1)
    map += ("Three" -> 3)
    map += ("One" -> 1)
    map += ("One" -> 1)
    
    val finalMap  = map.toMap
    finalMap.values.foreach(println)
    
  }
}