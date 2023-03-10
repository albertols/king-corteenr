package com.uem.clase

import com.unresolved.SparkUtils
import com.unresolved.reader.{AbstractReaderDf, CSVReader, JSONReader}
import com.unresolved.utils.MySleep.uemSleep
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, functions}

object Attendance extends SparkUtils {

  def main(args: Array[String]): Unit = {

    val claseCsvPath: String = getClass.getResource("/clase_2022_2023/clase.csv").getPath

    /**
     * =========================================================================================
     * TODO 1:
     * Go to CSVReader.scala class and complete the code so its capable of reading CSV files as a dataframe
     * =========================================================================================
     */
    val csvReader: AbstractReaderDf = CSVReader(options = Map(
      "header" -> "true",
      "inferSchema" -> "true"
    ))
    val csvDf = csvReader.readDf(claseCsvPath)
    val attendanceDf = csvDf.withColumn("attendance", lit(12))
    attendanceDf.printSchema
    attendanceDf.show
    spark.sqlContext.clearCache()
  }

}
