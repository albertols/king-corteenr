package com.db.preparation

import com.db.SparkUtils
import com.db.utils.MySleep.uemSleep
import com.resolved.ResolvedApplication.getClass
import com.resolved.reader.{CSVReader, ReaderDf}
import org.apache.spark.sql.DataFrame

object PreparationPreInterview extends SparkUtils {

  /**
   * App entrypoint
   *
   * @param args input arguments
   */
  def main(args: Array[String]): Unit = {
    val players1JSONPath: String = getClass.getResource("/players1.json").getPath
    val players2JSONPath: String = getClass.getResource("/players2.json").getPath
    val attackingPath: String = getClass.getResource("/attacking.csv").getPath
    val distributionPath: String = getClass.getResource("/distr.csv").getPath
    val goalkeepingPath: String = getClass.getResource("/goalkeeping.csv").getPath
    val goalsPath: String = getClass.getResource("/goals.csv").getPath
    val keyStatsPath: String = getClass.getResource("/key_stats.csv").getPath


    val testDf: DataFrame = spark // SparkContext
      .read
      .json(players1JSONPath)
    testDf.show(false)


    /**
     * =========================================================================================
     * TODO 1:
     * Go to CSVReader.scala class and complete the code so its capable of reading CSV files as a dataframe
     * =========================================================================================
     */
    val csvReader: ReaderDf = CSVReader(options = Map(
      "header" -> "true",
      "inferSchema" -> "true"
    ))
    val csvDf = csvReader.readDf(attackingPath)
    csvDf.show
    uemSleep(10000000)
    System.exit(-1)


    Thread.sleep(1000000)
    spark.sqlContext.clearCache()
  }

}
