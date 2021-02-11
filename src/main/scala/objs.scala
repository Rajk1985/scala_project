import org.apache.spark.sql._

import org.apache.spark.sql.functions._


object objs {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder.master("local[*]").appName("myaname").getOrCreate()
    val df =
    //spark.read
    // .option("header", "true")
    // .csv("F:\\Documents\\PLSQL Programs\\Hadoop\\Dataset\\us-500.csv")

      spark.read
        .option("header", "true")
        .format("csv").load("F:\\Documents\\PLSQL Programs\\Hadoop\\Dataset\\us-500.csv")

    df.printSchema()
    df.show()


  }


}
