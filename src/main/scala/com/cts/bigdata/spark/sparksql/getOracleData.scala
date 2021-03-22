package com.cts.bigdata.spark.sparksql

import org.apache.spark.sql._
import org.apache.spark.sql.functions._

object getOracleData {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.master("local[*]").appName("getOracleData").getOrCreate()
    //    val ssc = new StreamingContext(spark.sparkContext, Seconds(10))
    val sc = spark.sparkContext
    sc.setLogLevel("ERROR")
    import spark.implicits._
    import spark.sql
    //----------Write Logic Here--------------------------
    val url ="jdbc:oracle:thin://localhost:1521/orcl"
    //val df = spark.read.format("jdbc").option("url",url).option("user","scott")
       //      .option("password","tiger").option("driver","oracle.jdbc.OracleDriver").option("dbtable","DEPT").load()
    //df.show()
    //if u get class not found exception oracle.jdbc.OracleDriver ... go to file> project structure >> dependencies
    //    //+  add ojdbc7.jar ... ok

   // val qry = "Select * from dept where deptno = 10;"
   // val df = spark.read.format("jdbc").option("url",url).option("user","scott")
   //   .option("password","tiger").option("driver","oracle.jdbc.OracleDriver").option("dbtable","qry").load()
   // df.show()

    /**Writing data frm CSV file to Database ***/

    val data = "F:\\bigdata\\Dataset\\10000Records.csv"
    val df = spark.read.format("csv").option("header","true").option("inferSchema","true").load(data)
     val reg = "[^a-zA-Z0-9]"
     val cols = df.columns.map(x=>x.replaceAll(reg,""))

    //To put date in specific format use Format option
    val ndf = df.toDF(cols:_*).withColumn("DateofBirth",to_date($"DateofBirth","MM/dd/yyyy"))
      .withColumn("DateofJoining",to_date($"DateofJoining","MM/dd/yyyy"))

    //Write records to database
    ndf.write.mode(SaveMode.Overwrite).format("jdbc").option("url",url).option("user","scott").option("password","tiger")
      .option("driver","oracle.jdbc.OracleDriver").option("dbtable","my10krecords").save()


    //---------------------------------------------------
    spark.stop()
  }
}