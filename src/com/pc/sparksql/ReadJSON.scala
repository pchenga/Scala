package com.pc.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.types.StringType

object ReadJSON {
  
  def main(args:Array[String]):Unit = {
    
     val conf = new SparkConf()
      .setAppName("ReadJSON")
      .setMaster("local[*]") //VCores

    //Entry point for structured data processing
    val spark = SparkSession.builder()
      .config(conf)
      //.enableHiveSupport()   val hiveContext = new HiveContext(sc)
      .getOrCreate()
      
   val userDF = spark.read.json("E:/Scala_Durga/Vamshi_PIO_Support/user.json")   

   userDF.printSchema()
   //userDF.show()
   import spark.implicits._
  
   userDF.select(
         $"id",
         $"first_name",
         when(userDF.col("gender").isNull , "N/A").otherwise($"gender")
  )
  
  
  //udf
  //Lambda function  or anonymous func
  
  def validateNull(input:String):String ={
       input match{
         case a if(a==null) => "NA"
         case _ => input  
       }
       }
     
   val nullUDF:UserDefinedFunction = udf(validateNull _ ) 
   
 val nullUDF1 = udf((input:String) => {
     input match{
         case a if(a==null) => "NA"
         case _ => input  
       }
   }
 )
   
   //DF 
   userDF.select(
         $"id",
         $"first_name",
         nullUDF1($"gender").as("geneder") //
  )
     
  userDF.createOrReplaceTempView("user_table")
  
  //spark.udf.register("nullUDF2", validateNull _ )
  spark.udf.register("nullUDF2", (input:String) => {
     input match{
         case a if(a==null) => "NA"
         case _ => input  
       }
   }
  
  )
  spark.sql("SELECT id,first_name, nullUDF2(gender) as gender FROM user_table").show()
  
  
  }
  

}