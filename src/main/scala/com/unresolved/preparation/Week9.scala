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
    val dnaStudies = dna_100.select(col("annotation.alternate"), $"id", $"start", $"end", explode($"studies"))
      .withColumn("la_hora_de_ahora", current_timestamp())
      .withColumn("ts_unix_la_hora_de_ahora", unix_timestamp())
      .withColumn("t_valentina", from_unixtime($"end"))
    //.withColumn("t", to_timestamp(col("end"), "dd-MM-YYYY"))
    dnaStudies.show(false)


    dnaStudies.select($"col.studyId", $"id", $"end", $"t_valentina")
      .show(2000, false)
    dnaStudies.printSchema

    /**
     * TODO 1.2.1: upper case transformation of studyId
     */
    val upper_sol = dnaStudies.select(upper(col("col.studyId")).as("LIMBER_ID"))
    upper_sol.show(2, false)


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
    println("TODO SPARK 3: UNION:")
    val playersDf: DataFrame = players1Df.union(players2Df).distinct
    playersDf.show(10, false)


    /**
     * =========================================================================================
     * TODO 4:
     * Combine all the dataframes without losing any player's data.
     * 'goals' and 'assists' fields are shared between more than one file. Keep only the one coming from the 'key_stats.csv'.
     * =========================================================================================
     */
      println(s"TODO SPARK 4")
    val pks = Seq("player_name", "club", "position")
    val fullDf = playersDf
      .join(attackingDf.repartition(15).drop("assists"), pks, "left")
      .join(distributionDf, pks, "left")
      .join(goalkeepingDf, pks, "left")
      .join(goalsDf.drop("goals"), pks, "left")
      .join(keyStatsDf, pks, "left")

    assert(fullDf.count == playersDf.count)
    fullDf.printSchema()
    fullDf.show(10, false)


    /**
     * =========================================================================================
     * TODO 5:
     * Some players are missing the data.
     * Fill the string gaps with a 'not_available' keyword. The decimals with 0.0 and numbers with 0
     * Create a new function called 'fillGaps' and use it.
     * =========================================================================================
     */
    def fillGaps(df: DataFrame): DataFrame = df
      .na.fill("not_available")
      .na.fill(0.0)
      .na.fill(0)

    val curatedFullDf: DataFrame = fillGaps(fullDf)
    println("TODO 5 SPARK: curatedFullDf:")
    curatedFullDf.show(false)
    println("curatedFullDf:")

    /**
     * =========================================================================================
     * TODO 6:
     * Filter out all the players that have played less than 5 matches OR did not cover at least 5.5 of distance.
     * =========================================================================================
     */
      // match_played
      // distance_covered
    println("TODO SPARK 6: participatingPlayersDf:")
    val participatingPlayersDf_0 = curatedFullDf.filter("match_played>= 5 and distance_covered > lit(5.5)")
    val participatingPlayersDf = curatedFullDf.filter(col("match_played") >= 5 or col("distance_covered") > lit(5.5))
    participatingPlayersDf.show(false)

    /**
     * =========================================================================================
     * TODO 7:
     * From the participating players dataframe. Get the top 5 goalkeepers with the most saved / conceded ratio by descending order.
     * Select only primary keys and relevant columns
     * =========================================================================================
     */
    println("TODO SPARK 7: goaliesSaveConcedeRatioDf:")
    val goaliesSaveConcedeRatioDf = participatingPlayersDf
      .filter("position = 'Goalkeeper'")
      .withColumn("save_concede_ratio", col("saved")/col("conceded"))
      .sort(desc("save_concede_ratio"))
      .select("player_name", "club", "position", "save_concede_ratio")
    // select de p.k + save_concede_ratio



    /**
     * =========================================================================================
     * TODO 8:
     * Get the number of players that play on each position
     * =========================================================================================
     */
    println("TODO SPARK 8: Num Players By Pos:")
    curatedFullDf.groupBy("position").count().show

    /**
     * =========================================================================================
     * TODO 9:
     * Get the distance covered per minute on each team
     * =========================================================================================
     */
    println("TODO 9 SPARK: Club distances per minute:")
    curatedFullDf
      .groupBy("club")
      .agg(
        sum("minutes_played"),
        sum("distance_covered")
      )
      .withColumn("distance_covered_per_minute", col("total_distance_covered") / col("total_minutes_played"))
      .show(false)



    /**
     * =========================================================================================
     * TODO 10:
     * Get the most scorer by each team per position. Use Window.partitionBy() spark function.
     * Filter out the ones with 0 goals. Order by club and position in ascending order
     * Select only relevant columns
     * =========================================================================================
     */
    println("TODO SPARK 10: Most Scorer by club and position:")
    curatedFullDf
      .withColumn("most_goals", functions.max(col("goals")).over(Window.partitionBy("club", "position")))
      .filter(col("most_goals") === col("goals"))
      .filter(col("goals") > 0)
      .select("player_name", "club", "position", "most_goals")
      .orderBy(asc("club"), asc("position"))
      .show(false)
    uemSleep(10000000)
    spark.sqlContext.clearCache()
    System.exit(-1)

    /**
     * =========================================================================================
     * TODO 11:
     * Group all the players that belong to a club in a single 'array' column named "players"
     * =========================================================================================
     */
    println("TODO SPARK 11: Players by Club:")
    curatedFullDf.groupBy("club").agg(collect_set("player_name").as("players")).show(false)


    /**
     * =========================================================================================
     * TODO 12:
     * As metadata add the following columns to the 'curatedFullDf':
     * - A new column 'id' with a unique identifier for each record
     * - A new column 'execution_date' with the current date in a ISO Date Format
     * =========================================================================================
     */
    println("TODO SPARK 12: curatedFullDf with metadata:")
    val withMetadataDf = curatedFullDf
      .withColumn("id", expr("uuid()"))
      .withColumn("id_peligroso", monotonically_increasing_id)
      .withColumn("execution_date", date_format(current_date(), "yyyy-MM-dd"))

    println("TODO 11 SPARK: uratedFullDf with metadata:")
    withMetadataDf
      .show(false)


    /**
     * =========================================================================================
     * TODO 13:
     * Write the dataframe partitioning by club in parquet format on './landing_area/'
     * Use the SaveMode Overwrite
     * =========================================================================================
     */
    withMetadataDf
      .write
      .partitionBy("club", "execution_date")
      .mode(SaveMode.Overwrite)
      .parquet("./landing_area")

    uemSleep(10000000)



    spark.sqlContext.clearCache()
  }

}
