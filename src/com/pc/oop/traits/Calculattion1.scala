package com.pc.oop.traits

//1. Purely abstracted
//2. Can't created an object
//3. Should be sub classed
//4. static members , abstract, default methods  
//5. Access Calculattion.MIN_VAL, Calculattion.fun
//6. Doesn't have constructor
//7. trait extends trait
//8. class extends trait1 with trait2 with trait3

trait Calculattion1 {
  val MIN_VAL =100 
  val MAX_VAL =200
  
  def sum(a:Int, b:Int):Int //abstract 
  
  //default methods 
  def add(a:Int, b:Int):Int = {
    a+b
  }
}