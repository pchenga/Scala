package com.pc.oop.traits

object TraitTest  {
  
  def main(args:Array[String]):Unit ={
    
    //val calc = new Calculattion()
    val a:Addition1 = new Addition1()
    println(a.sum(10,20))
    println(a.sum1(100,200))
    println(a.add(1, 2))
        
    //Dynamic dispatch
    val a1:Calculattion1 = new Addition1()
    println(a1.sum(10,20))
    //println(a1.sum1(100,200))
    println(a1.add(1,2))
    
 
    
  }
  
}