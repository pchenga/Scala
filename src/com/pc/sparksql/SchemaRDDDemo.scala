package com.pc.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.Row
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.StringType
import scala.io.Source

object SchemaRDDDemo {
  
  def main(args:Array[String]):Unit = {
    
      val conf = new SparkConf()
               .setAppName("SchemaRDDDemo")
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
   
    //1. RDD[String] => RDD[Row]
    
   val rowRDD: RDD[Row] =  inputRDD.map(line => {
     val cols:Array[String] = line.split(",")
     Row(cols(0),cols(1),cols(2))
    }
    )
    
    //2 Schema
   /* val userSchema = StructType(
                      Array(
                       StructField("userId", StringType,true),
                       StructField("productId", StringType,true),
                       StructField("visitorId", StringType,true)
                       )
                     )*/
    
   val inputLines = Source.fromFile("E:/Scala_Durga/Vamshi_PIO_Support/app.properties").getLines()
   
   var schema:StructType = null
   
   while(inputLines.hasNext)
   {
     val input = inputLines.next()
     schema = StructType(input.split(",").map(field => StructField(field, StringType,true)))
   }
   
   
    //1 RDD[Row] + 2 schema =  3 DataFrame
                     
   val userDF =  spark.createDataFrame(rowRDD, schema)  
   
   userDF.printSchema
   userDF.show
   
   inputLines.foreach(println)
  }
  
}