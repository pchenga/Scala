package com.pc.collections

object FoldOperations {

  def main(args:Array[String]):Unit = {
    
    val nums:Seq[Int] = Seq(1,2,3,4,5,6,7,8,9,10)
    
    //1. reduce, reduceLeft, reduceRight
    //2. fold(0), foldLeft(0), foldRight(0)
    
    println(nums.reduce((acc, other) => acc +other))
    println(nums.reduceLeft((acc, other) => acc +other))
    println(nums.reduceRight((acc, other) => acc +other))
    
   val x =  nums.fold(1)(_+_)
   val y =  nums.foldLeft(1)(_+_)
   val z =  nums.foldRight(1)(_+_)
   
   println(s"x =$x y=$y z=$z")
   
   val nums1:Seq[Seq[Int]] = Seq(
                               Seq(1,2,3,4),
                               Seq(5,6,7,8),
                               Seq(9,10,11,12)
                                  )
    nums1.foldLeft(Seq.empty[Int])(_++_).foreach(println)
                                  
  }
  
}
