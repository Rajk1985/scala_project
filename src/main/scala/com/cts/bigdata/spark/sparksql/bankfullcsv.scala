package com.cts.bigdata.spark.sparksql

import org.apache.spark.sql._
import org.apache.spark.sql.functions._

object bankfullcsv {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.master("local[*]").appName("bankfullcsv").getOrCreate()
    //    val ssc = new StreamingContext(spark.sparkContext, Seconds(10))
    val sc = spark.sparkContext
    sc.setLogLevel("ERROR")
    import spark.implicits._
    import spark.sql
    //----------Write Logic Here--------------------------

    val data = "F:\\bigdata\\Dataset\\bank-full.csv"
    val df = spark.read.format("csv").option("header","true").option("inferSchema","true").option("delimiter",";").load(data)


    //option("delimiter",";") --<-- This is used incase the column seperator is not comma(,)

    //df.show(2)

    df.createTempView("tab")
    //val res = spark.sql ("select * from tab where age > 85" )
    val res = df.where($"age" >85)

    res.show()

    // to add extra column we use "withColumn"
    val res1 = df.withColumn("uid", monotonically_increasing_id())

    res1.show()


    //---------------------------------------------------
    spark.stop()
  }
}