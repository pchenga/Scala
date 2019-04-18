package com.pc.collections

object MapTest {
  
  def main(args:Array[String]):Unit = {
    
    //(K,V)
    val map = Map[String,Int](("One",1),("Two",2),("Three",3))
    
    println(map.isEmpty)
    println(map.size)
    
    //Access -- Know the key 
    val value1 = map.get("One")
    println(value1)
    
     val value2 = map("One")
     println(value2)
   
    //Values
    val values1:Iterable[Int] = map.values
    
    values1.foreach(e => {
     println(e*e) 
    }
       
    )
    
     
    val itr = map.valuesIterator
    while(itr.hasNext){
      println(itr.next())
    }
    
    //Keys 
    
    val keys1 = map.keys.take(2)
    
    keys1.foreach(k => {
     val v = map(k)
     println(k +"\t" + v) 
    }
    )
    
       
    
    
    
  }
  
}