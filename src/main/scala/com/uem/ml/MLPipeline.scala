package com.uem.ml

import com.unresolved.SparkUtils

/**
 *
 * https://github.com/spark-in-action/first-edition/blob/master/ch07/housing.data
 */
object MLPipeline extends SparkUtils {

  def main(args: Array[String]): Unit = {
    import org.apache.spark.mllib.linalg.Vectors
    /*
    Inputs are also called examples, points, data samples, observations, or
    instances. In Spark, training examples for supervised learning are called
    labeled points. Features (sepal length and sepal width in the Iris dataset,
    for example) are also called dimensions, attributes, variables, or
    independent variables.
     */
    val casas = spark.sparkContext.textFile(getClass.getResource("/ml/housing.data").getPath, 6)
    casas.foreach(println)
    val casasValores = casas.map(x => Vectors.dense(x.split(",").map(_.trim().toDouble)))
  }

}
