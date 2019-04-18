package com.pc.collections

import scala.collection.mutable.ListBuffer

object ListBufferTest {
  
  def main(args:Array[String]):Unit = {
    
    val buff = new ListBuffer[Int]()
    
    buff += 1
    buff += 2
    buff += 3
    buff += 1
    
    
    buff.foreach(println)
    
    //covert to mutable to immutable
    val myList = buff.toList
    
    
  }
}