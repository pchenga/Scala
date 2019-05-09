package com.pc.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.Row
import org.apache.spark.sql.catalyst.encoders._
import org.apache.spark.sql.Encoders

object CreateDS {

  
  def main(args:Array[String]):Unit ={
    
     val conf = new SparkConf()
      .setAppName("CreateDS")
      .setMaster("local[*]") //VCores

    //Entry point for structured data processing
    val spark = SparkSession.builder()
      .config(conf)
      //.enableHiveSupport()   val hiveContext = new HiveContext(sc)
      .getOrCreate()
      
      
    import spark.implicits._                         
   //RDD --core SparkContext 
   
   spark.sparkContext.setLogLevel("OFF")
                            
   //Scala Collections
   val nums = (1 to 100).toList  
   
   val ds1 = nums.toDS()
   ds1.printSchema
   ds1.show

   
   val users = Seq(("u1","p1",1),("u2","p2",1),("u3","p3",3),("u4","p4",1),("u5","p5",5))
   
   val df:Dataset[Row]= users.toDF("uid","pid","vid")
      
   //users.toDF("uid","pid","vid")
   
    val inputRDD:RDD[String] = spark.sparkContext.textFile("E:/Scala_Durga/Vamshi_PIO_Support/MOCK_DATA.csv")                       
   
    //remove the header from input file
    //4
    //0 -- ( (userId,),(1,1,1),(2,2,2))...... 
    //1 -- (111,111,1)
    
   val withOutHeadrRDD =  inputRDD.mapPartitionsWithIndex((index, iterator) => {
      if(index == 0 )
        iterator.drop(1) // 
        
      iterator
    }
    )
    
    //RDD[String] => RDD[User]
   val userRDD:RDD[User] =  withOutHeadrRDD.map(line => {
      val cols = line.split(",")
     User(cols(0),cols(1),cols(2)) 
    }
    )
   
    
  
  val userDS =  spark.createDataset(userRDD)
  
  userDS.printSchema
  userDS.show
  
  val userList = userDS.collect().toList
  
  spark.createDataset(userList).show
    
   
  }
  
}