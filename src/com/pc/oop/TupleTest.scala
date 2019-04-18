package com.pc.oop

object TupleTest {
  
  def  main(args:Array[String]) : Unit = {
    
    val i:Int = 10
    val name:String = "Abc"
    
    //Option Wrapper some(1) or None 
    //Possible values Some(i) else None
    
    //val j:Option[Int] = Some(1) //positive None Negative
    val j:Option[Int] = None
    
    
    
    //Store different type of values 
    
    //Tuple ()
    //._1
    //._2 
    val cust:(Int,Option[String]) = (1,Some("Vamshi"))
    println(cust._1)
    println(cust._2)
    
    
    
    val cust1:Option[(Int,Option[String])]= Some(cust)
    
    val extractValue = j.getOrElse() // If you have Some(1)
    println(extractValue)
    
    
    val extractValue1 = j.getOrElse(0) // If you have Some(1)
    println(extractValue1)
    
    
    println(cust1.get._2.get)
    
    
  }
}