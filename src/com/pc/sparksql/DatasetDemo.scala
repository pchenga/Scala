package com.pc.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Dataset

//T - Statically type
case class User(userId:String, productId:String, visitorId:String)

object DatasetDemo {
  
  def main(args:Array[String]):Unit ={
    
     val conf = new SparkConf()
      .setAppName("DatasetDemo")
      .setMaster("local[*]") //VCores

    //Entry point for structured data processing
    val spark = SparkSession.builder()
      .config(conf)
      //.enableHiveSupport()   val hiveContext = new HiveContext(sc)
      .getOrCreate()
      
      
      import spark.implicits._                         
   //RDD --core SparkContext 
   
   spark.sparkContext.setLogLevel("OFF")
                            
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
    
    //Similar to RDD API + SQL(DataFrame) 
    val userDS:Dataset[User] = userRDD.toDS()
    
    userDS.createOrReplaceTempView("user_table")
    
    val ds1 = spark.sql("SELECT * FROM user_table")
    ds1.printSchema
    ds1.show
    
    userDS.filter(user => user.productId.equals("p2")).show()
    
    val filteredUserRDD:RDD[User] = userRDD.filter(user => user.userId.equals("2"))
    filteredUserRDD.foreach(user => println(user))
  } //main
  
} //object