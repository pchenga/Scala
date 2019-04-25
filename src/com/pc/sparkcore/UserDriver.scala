package com.pc.sparkcore

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel



object UserDriver {

  def sum(a:Int, b:Int):Int = a + b
  
  def main(args:Array[String]):Unit = {

        
    val conf = new SparkConf()
               .setAppName("UserDriver")
               .setMaster("local[*]") //VCores
    
    //Entry point for structured data processing 
    val spark = SparkSession.builder()
                            .config(conf) 
                            //.enableHiveSupport()   val hiveContext = new HiveContext(sc)
                            .getOrCreate()
                            
    //RDD --core SparkContext  
                            
    val inputRDD:RDD[String] = spark.sparkContext.textFile("E:/Scala_Durga/Vamshi_PIO_Support/MOCK_DATA.csv")                       
    
    inputRDD.take(10).foreach(l => println(l))
    
    //user wise count
    //RDD[String] => RDD[(String,Int)]
    //1,p1,1   => (1,1)
    val pairRDD: RDD[(String,Int)] = inputRDD.map((line:String) => {
      val cols = line.split(",")
      (cols(0),1)
      }
    )
    
    
    pairRDD.foreach( t => println(t._1 +"\t" + t._2))
    
    
    //groupByKey 
   /* val groupByRDD = pairRDD.groupByKey()
    groupByRDD.foreach(t => println(t._1 +"\t" + t._2.size)) */
    
    //groupByKey + sum 
    //(1, List(1,1,1))  3
    //(2, List(1,1,1))
    
    val nums = List(1,2,3,4,5)
    val res = nums.reduce((a,b) => a+b)
    
    val userCountRDD = pairRDD.reduceByKey(sum _) // Inline func or anonymous func or lambda
    userCountRDD.foreach(t => t._1 +"\t" + t._2)
    
    pairRDD.reduceByKey(_+_).foreach(println)
    
   val f1 =  pairRDD.filter((t:(String,Int)) => (t._1.equals("1"))) // Lazy Evaluate
    
   f1.cache()   // MEMORY_ONLY  RDD only
   f1.persist(StorageLevel.MEMORY_AND_DISK) 
   val res1 = f1.collect() // action1
   val res2 = f1.take(5)   // action2
   
   f1.repartition(50)
   f1.coalesce(5) 
   
    
    //1. Transformations (map, flatMap, filter .....) -- returns RDD
    //      Narrow transformation (map, flatMap, filter)
    //      Wide transformation   (groupByKey,reduceByKey,repartition,coalesce) 
    //2. Actions(collect, take, foreach)              -- returns no rdd values -- Map, Array 
    //3. Other Operation (cache, persist())           --
    
    //RDD[String] => RDD[User] => To DataFrame 
    
  }
  
}