package com.db.preparation

import com.db.SparkUtils
import org.apache.spark.sql.DataFrame

object PreparationPreInterview extends SparkUtils {

  /**
   * App entrypoint
   *
   * @param args input arguments
   */
  def main(args: Array[String]): Unit = {
    val players1JSONPath: String = getClass.getResource("/players1.json").getPath
    val testDf: DataFrame = spark
      .read
      .json(players1JSONPath)
    testDf.show(false)
    spark.sqlContext.clearCache()
  }

}
