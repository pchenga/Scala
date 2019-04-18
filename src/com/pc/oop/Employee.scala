package com.pc.oop

//Non static members
//Create an object (val e= new Employee(1,s,))
//User Defined Type
//Primary Constructor 
class Employee( val eid:Int =1 ,val ename:String ="NA", val sal:Double=0.0){
  
  //Fields or variable or instance
  /*val empId:Int = eid
  val empName:String = ename
  val empSal:Double = sal*/
  
  //Secondary Constructor
  def this(eid:Int,ename:String){
    this(eid,ename,0.0) // Rotuing to primary constructor
  }
  
   def this(eid:Int){
    this(eid,"NA",0.0) // Rotuing to primary constructor
  }
  
  
  //business logic methods
  
  def printEmp():Unit ={
    println(s"empId = $eid, empName=$ename, empSal=$sal")
  }
  
  //def m1():Unit 
  
}