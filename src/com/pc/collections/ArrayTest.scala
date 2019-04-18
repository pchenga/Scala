package com.pc.collections

object ArrayTest {
  
  var result = 0
  def sum(nums:Int*):Int = {
    for(n <- nums){
      result += n
    }
   result
  }
  
  
  def myPrint(n:Int) = println(n)
  
  //(n:Int) => println(n)
  
  def main(args:Array[String]):Unit = {
  
    println(sum(1,2,3,4,5))
    
    val arr = Array[Int](1,2,3,4,5)
    
    println(arr(0))
    println(arr(1))
    println(arr(2))
    
    //for( n <- arr) println(n)
    
    //arr.foreach(myPrint(_))
    
    //arr.foreach(println)
    
    arr.foreach((n:Int) => println(n))
        
    //arr foreach println
    
    
    
  }
}