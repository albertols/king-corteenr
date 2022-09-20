package com.db.reader

import org.apache.spark.sql.{DataFrame, SparkSession}

case class CSVReader() extends AbstractReaderDf {

  override def readDf(path: String)(implicit spark: SparkSession): DataFrame = ???

}
