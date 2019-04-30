package com.pc.sparksql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import scala.io.Source

object ReadCSV {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("ReadCSV")
      .setMaster("local[*]") //VCores

    //Entry point for structured data processing
    val spark = SparkSession.builder()
      .config(conf)
      //.enableHiveSupport()   val hiveContext = new HiveContext(sc)
      .getOrCreate()
      
      
     val df = spark
               .read
               .option("header","true")
               .option("inferSchema", "true")
               .option("sep",",")
               .csv("E:/Scala_Durga/Vamshi_PIO_Support/MOCK_DATA.csv") 
     df.printSchema
     df.show
  }
}