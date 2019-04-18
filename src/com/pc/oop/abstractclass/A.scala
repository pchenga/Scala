package com.pc.oop.abstractclass

trait T1
trait T2

class A  extends Calculaction with T1 with T2 {
  
  def sum(a:Int, b:Int):Int = {
    a+ b
  }
  
}