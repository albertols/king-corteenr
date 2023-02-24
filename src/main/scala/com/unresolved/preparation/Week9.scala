package com.unresolved.preparation

import com.unresolved.SparkUtils
import com.unresolved.reader.{AbstractReaderDf, AvroReader, CSVReader, JSONReader}
import com.unresolved.utils.MySleep.uemSleep
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SaveMode, functions}

object Week9 extends SparkUtils {

  def main(args: Array[String]): Unit = {

    val players1JSONPath: String = getClass.getResource("/players1.json").getPath
    val players2JSONPath: String = getClass.getResource("/players2.json").getPath
    val attackingPath: String = getClass.getResource("/attacking.csv").getPath
    val distributionPath: String = getClass.getResource("/distr.csv").getPath
    val goalkeepingPath: String = getClass.getResource("/goalkeeping.csv").getPath
    val goalsPath: String = getClass.getResource("/goals.csv").getPath
    val keyStatsPath: String = getClass.getResource("/key_stats.csv").getPath

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
    val csvDf=csvReader.readDf(attackingPath)
    csvDf.show

    /**
     * =========================================================================================
     * TODO 1.1:
     * Reads avro under /avro... does AvroReader works?
     * =========================================================================================
     */
    val avroReader = AvroReader()
    val twits = avroReader.readDf(getClass.getResource("/avro/twits.avro").getPath)
    twits.show
    twits.printSchema()
    val dna_100 = avroReader.readDf(getClass.getResource("/avro/dna_100.avro").getPath)
    dna_100.show
    dna_100.printSchema()

    /**
     * =========================================================================================
     * TODO 1.2:
     * selecting fields from avro, implicits, timestamps conversions, exploding arrays
     * =========================================================================================
     */
    import spark.implicits._


    /**
     * =========================================================================================
     * TODO 2:
     * Load files data as dataframes with the correct column types.
     * Be aware that 'players1.json' file is not in CSV format
     * =========================================================================================
     */
    val jsonReader = JSONReader()

    val players1Df: DataFrame = jsonReader.readDf(players1JSONPath)
    val players2Df: DataFrame = jsonReader.readDf(players2JSONPath)
    val attackingDf: DataFrame = csvReader.readDf(attackingPath)
    val distributionDf: DataFrame = csvReader.readDf(distributionPath)
    val goalkeepingDf: DataFrame = csvReader.readDf(goalkeepingPath)
    val goalsDf: DataFrame = csvReader.readDf(goalsPath)
    val keyStatsDf: DataFrame = csvReader.readDf(keyStatsPath)

    /**
     * =========================================================================================
     * TODO 3:
     * Combine players1 and players2 dataframes into a single one and get rid of the duplicates
     * =========================================================================================
     */
    val playersDf: DataFrame = ???

    println("UNION:")

    /**
     * =========================================================================================
     * TODO 4:
     * Combine all the dataframes without losing any player's data.
     * 'goals' and 'assists' fields are shared between more than one file. Keep only the one coming from the 'key_stats.csv'.
     * =========================================================================================
     */
    val pks = Seq("player_name", "club", "position")
    val fullDf = ???


    /**
     * =========================================================================================
     * TODO 5:
     * Some players are missing the data.
     * Fill the string gaps with a 'not_available' keyword. The decimals with 0.0 and numbers with 0
     * Create a new function called 'fillGaps' and use it.
     * =========================================================================================
     */
    def fillGaps(df: DataFrame): DataFrame = ???
    println("curatedFullDf:")

    /**
     * =========================================================================================
     * TODO 6:
     * Filter out all the players that have played less than 5 matches or did not cover at least 5.5 of distance.
     * =========================================================================================
     */
    val participatingPlayersDf = ???

    /**
     * =========================================================================================
     * TODO 7:
     * From the participating players dataframe. Get the top 5 goalkeepers with the most saved / conceded ratio by descending order.
     * Select only primary keys and relevant columns
     * =========================================================================================
     */
    val goaliesSaveConcedeRatioDf = ???

    println("goaliesSaveConcedeRatioDf:")

    /**
     * =========================================================================================
     * TODO 8:
     * Get the number of players that play on each position
     * =========================================================================================
     */
    println("Num Players By Pos:")

    /**
     * =========================================================================================
     * TODO 9:
     * Get the distance covered per minute on each team
     * =========================================================================================
     */
    println("Club distances per minute:")

    /**
     * =========================================================================================
     * TODO 10:
     * Get the most scorer by each team per position. Use Window.partitionBy() spark function.
     * Filter out the ones with 0 goals. Order by club and position in ascending order
     * Select only relevant columns
     * =========================================================================================
     */
    println("Most Scorer by club and position:")

    /**
     * =========================================================================================
     * TODO 11:
     * Group all the players that belong to a club in a single 'array' column named "players"
     * =========================================================================================
     */
    println("Players by Club:")

    /**
     * =========================================================================================
     * TODO 12:
     * As metadata add the following columns to the 'curatedFullDf':
     * - A new column 'id' with a unique identifier for each record
     * - A new column 'execution_date' with the current date in a ISO Date Format
     * =========================================================================================
     */
    val withMetadataDf = ???

    println("curatedFullDf with metadata:")

    /**
     * =========================================================================================
     * TODO 13:
     * Write the dataframe partitioning by club in parquet format on './landing_area/'
     * Use the SaveMode Overwrite
     * =========================================================================================
     */



    spark.sqlContext.clearCache()
  }

}
