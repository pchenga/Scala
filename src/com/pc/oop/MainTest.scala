package com.pc.oop

import scala.io.StdIn

import scala.collection.immutable.List

//singleton 
//static 
//PascalCase
object MainTest {
  
  val MIN_VAL = 1
  val MAX_VAL = 999
  
  //static function
  def sum(n1:Int, n2:Int):Int = {
    n1+n2
  }
  
  //static method
  def main(args:Array[String]):Unit ={
    
    val i = StdIn.readInt()
    val j = StdIn.readInt()
    
    val sum = i + j
    
    println(s"Sum of the two numbers $sum")
    
    
    
    
  }
  
}
