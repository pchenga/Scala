package com.pc.oop.abstractclass

//instance member
//Not purely abstract
abstract class Calculaction {
  val i=100 
  val j=200
  
  
  //concrete methods 
  def add(a:Int, b:Int):Int = {
    a+b
  }
  
   
  def sum(a:Int, b:Int):Int //abstract
}