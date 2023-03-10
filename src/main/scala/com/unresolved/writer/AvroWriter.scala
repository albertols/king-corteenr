package com.unresolved.writer
import org.apache.spark.sql.{DataFrame, SparkSession}

case class AvroWriter() extends  AbstractWriterDf {
  override def writeDf(path: String)(implicit spark: SparkSession): DataFrame = ???


}
