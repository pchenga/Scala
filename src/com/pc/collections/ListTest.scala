package com.pc.collections

object ListTest {
  
  def main(args:Array[String]):Unit = {
    
    val nums = List(1,2,3,4,5,6)
    
    for( n <- nums) 
      println(n)
      
      
    nums.foreach(println)  
    nums.take(2).foreach(println)
    
    val newList = nums.drop(4)
    newList.foreach(println)
    
    val nums1 = List(7,8,9)
    
    val finalList = nums ++ nums1
    
      
    
  }
}