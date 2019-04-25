package com.pc.sparkcore

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD


case class User(userId:String, productId:String, visitorId:String)

object RDDToDataFrame {
  
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
                            
    val inputRDD:RDD[String] = spark.sparkContext.textFile("E:/Scala_Durga/Vamshi_PIO_Support/MOCK_DATA.csv")                       
    
    //RDD[String] => RDD[User]
   val userRDD:RDD[User] =  inputRDD.map(line => {
     val cols = line.split(",") 
     User(cols(0),cols(1),cols(2))
    }
    )
    
    val userDF = userRDD.toDF()
    userDF.printSchema()
    userDF.show()
     
    
    //DataFrame 
    
  }
}