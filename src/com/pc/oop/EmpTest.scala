package com.pc.oop

object EmpTest {
  
  def main(args:Array[String]):Unit ={
    val e1 = new Employee(1,"Test",111.11)
    e1.printEmp()
    
    val e2 = new Employee(1,"Test1")
     e2.printEmp()
     
     val e3 = new Employee(2)
     e3.printEmp()
     println(e3.eid)
     println(e3.ename)
     println(e3.sal)
     
     
     val e4 = new Employee()
     e4.printEmp()
    
    
    
  }
}