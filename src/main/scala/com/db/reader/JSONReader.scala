package com.db.reader

import org.apache.spark.sql.{DataFrame, SparkSession}

case class JSONReader(options: Map[String, String] = Map()) extends AbstractReaderDf {

  override def readDf(path: String)(implicit spark: SparkSession): DataFrame = spark
    .read
    .options(options)
    .json(path)

}
