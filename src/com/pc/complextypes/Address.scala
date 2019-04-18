package com.pc.complextypes

class Address( val street:String, val city:String){
 
 override def toString():String ={
   println("Address toString is called...") 
   s"street=$street city=$city"  
  }
}

