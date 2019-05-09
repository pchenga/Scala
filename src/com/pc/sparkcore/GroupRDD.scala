package com.pc.sparkcore

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object GroupRDD {
  
  def main(args:Array[String]):Unit = {
    
   val conf = new SparkConf()
               .setAppName("RDDToDataFrame")
               .setMaster("local[*]") //VCores
    
    //Entry point for structured data processing 
    val spark = SparkSession.builder()
                            .config(conf) 
                            //.enableHiveSupport()   val hiveContext = new HiveContext(sc)
                            .getOrCreate()
   
   import spark.implicits._                         
   //RDD --core SparkContext 
   spark.sparkContext.setLogLevel("OFF")
   
   val input1 = Seq(
    (1,"One"),
    (2,"Two"),
    (3,"Three"),
    (8,"Eight"),
    (9,"Nine")
   )
   
   val input2 = Seq(
    (1,"One"),
    (2,"Two"),   
    (4,"Four"),
    (5,"Five"),
    (6,"Six"),
    (7,"Seven")
    
   )
  
   val df1 = input1.toDF("id","value")
   val df2 = input2.toDF()
   
   df1.show
   df2.show
 
   val rdd1 =  df1.rdd
  val rdd2 =  df2.rdd
  
 val pairRDD1 =  rdd1.map(r => (r.get(0),r.get(1)))
 val pairRDD2 =  rdd2.map(r => (r.get(0),r.get(1)))
 
 pairRDD1.foreach(println)
 pairRDD2.foreach(println)
 
 println("Group Operation")
 val cogroupRes = pairRDD1.cogroup(pairRDD2)
 cogroupRes.foreach(println)
 
  println("Join Operation")
 val joinRes = pairRDD1.join(pairRDD2)
 joinRes.foreach(println)                           
  
    
  }
  
}