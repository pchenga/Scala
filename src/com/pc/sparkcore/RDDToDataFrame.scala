package com.pc.sparkcore

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.rdd.RDD

//Schema
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
   
   spark.sparkContext.setLogLevel("OFF")
                            
    val inputRDD:RDD[String] = spark.sparkContext.textFile("E:/Scala_Durga/Vamshi_PIO_Support/MOCK_DATA.csv")                       
    
    //RDD[String] => RDD[User]
   val userRDD:RDD[User] =  inputRDD.map(line => {
     val cols = line.split(",") 
     User(cols(0),cols(1),cols(2))
    }
    )
    /*
    RDD[User,  val user = User(1,p1,1)
    User,
    User,
    User...] */
    userRDD.foreach(user => println(s"userId = ${user.userId} productId =${user.productId}") )
    
    val userDF = userRDD.toDF()
    //userDF.printSchema()
    //userDF.show()
    
    
    //DataFrame = RDD[Row] + User(userId:String, productId:String, visitorId:String)
    //1. SQL API
    
    userDF.createOrReplaceTempView("user_table")
    
    userDF.as("u")
    
    val df1 = spark.sql("SELECT userId,productId FROM user_table")
    df1.printSchema()
    df1.show()
    
    /*
    RDD[Row,
    Row,
    Row...]
    
    Row[col0,col1...] getXXX getSting
    
    */
    
    df1.foreach(r => println(r.getString(0) +"\t" + r.getString(1)))
        
    //2. DataFrame/DSL API  Column, Column 
    userDF.select("userId","visitorId").show()
    userDF.select(userDF.col("userId"),userDF.col("visitorId")).show()
    userDF.select(
          $"userId",
          $"productId",
          $"visitorId"
        ).show()
        
        
    //ProductDF
    val products = Seq(
        ("p1","Laptop"),
        ("p2","TV"),
        ("p3","Projector")
        )  
     
   val productDF = products.toDF("productId","productName")     
   
   productDF.printSchema
   productDF.show
   
   
   val finalDF = userDF.join(productDF,userDF.col("productId") === productDF.col("productId") ,"left_outer")
   
   finalDF.printSchema()
   finalDF.show()
   
   
 val resDF=
       finalDF.select(
       userDF.col("userId"),  
       userDF.col("visitorId"),   
       productDF.col("productId"),
       when(productDF.col("productName").isNull, "NA").otherwise(productDF.col("productName")).as("productDes")
       )
       
  resDF.printSchema
  resDF.show
   
        
     
    
    
  }
}