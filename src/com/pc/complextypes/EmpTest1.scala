package com.pc.complextypes

object EmpTest1 {
 
  def main(args:Array[String]):Unit = {
    
    val emailList = List("test1@test1.com","test2@test2.com","test3@test3.com")
    
    val address1 = new Address("krpuram","bangalore")
    val address2 = new Address("m g road","bangalore")
    
    val referenceMap = Map[String,Address](("ram",address1),("krishna",address2))
    
    
    val emp1 = new Emp(1,"abc",emailList,referenceMap)
    
    println(emp1.eid)
    println(emp1.ename)
    println(emp1.refers) //Map.toString()  (String.toString()-> Address.toString())
    
    emp1.emails.foreach(e => println(e))
    //Map(("prakash",Address("f","")),("prakash",Address("f","")))
    //("prakash",Address("f",""))
    emp1.refers.foreach{ case (name,address) => 
      println(name)
      println(address)
    }
    
    val m = emp1.refers
    val keys1 = m.keys
    
    keys1.foreach(k => {
      val add = m(k)
      val street = add.street
      val city = add.city
      
      println(k + "\t" + street + "\t" + city)
      
    }   
    )
    
    
    
    
    
    
    
  }
}