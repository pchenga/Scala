package com.pc.oop

//Schema 
case class Customer(val cid:Int, val cname:String) {
  def printCust():Unit = {
    println(s"cid = $cid cname = $cname")
  }
  // apply takes constructors params and create an object
  //unapply -- 
  
  def unapply(c:Customer) : Option[(Int,String)]={
    c match{
      case Customer(id,name) => Some(id,name)
      case _ => None
    }
    
  }
  
}

//static 
object Constants {
  val ALL = "all"
  val CS = "cs"
  val ES = "es"
}

object CustomerTest{
  
  def main(args:Array[String]):Unit = {
    
    val c1 = new Customer(1,"test") //valid
    c1.printCust()
    println(c1.cid)
    
    val c2 = Customer(2,"Test") //valid
    c2.printCust()
    
    val c3 = Customer.apply(3,"Test") //valid
    c3.printCust()
    
    //Pattern Matching 
    
    // 1. value match
    // 2. variable match expression
    // 3. case class
    // 4. Type matching
    
    val name:String = "all" // ap.
    
    name match {
      case  Constants.ALL=> println("This is all")
      case  Constants.CS  => println("cs")
      case  _   => println("others")
    }
    
    
    name match {
      case a if(a.equals("all")) =>  println("This is all2")
      case a if(a.equals("cs"))  => println("cs")
      case _ => println("others")
    }
    
    //case class 
    
    c1 match {
      case Customer(1, "test") => println("Customer1")
      case Customer(1, "test") => println("Customer2")
      case _ => println("others")
    }
    
    
    c2 match{
      case Customer(a, "Test") => println(s"This is $a")
      case Customer(1, a) => println(s"This is $a")
      case _ => println("others")
    }
    
    
     name match {
      case  a:String => println("This is String")
      case  _   => println("others")
    }
     
     
     println(c1.unapply(c2))
    
    
    
  }
  
  
}
