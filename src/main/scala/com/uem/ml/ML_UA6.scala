package com.uem.ml

import com.unresolved.SparkUtils
import org.apache.spark.mllib.regression.LinearRegressionWithSGD
import org.apache.spark.sql.Row
import org.apache.spark.sql.functions.{col, explode}
import org.apache.spark.sql.types.{DoubleType, StructField, StructType}

object ML_UA6 extends SparkUtils {

  val sc = spark.sparkContext

  val schema = new StructType()
    .add(StructField("CRIM", DoubleType, false)) //1.CRIM: per capita crime rate by town
    .add(StructField("ZN", DoubleType, true)) //2.ZN: proportion of residential land zoned for lots over 25,000 sq.ft.
    .add(StructField("INDUS", DoubleType, true)) //3.INDUS: proportion of non-retail business acres per town
    .add(StructField("CHAS", DoubleType, true)) //4.CHAS: Charles River dummy variable (= 1 if tract bounds  river; 0 otherwise)
    .add(StructField("NOX", DoubleType, true)) //5.NOX: nitric oxides concentration (parts per 10 million)
    .add(StructField("RM", DoubleType, true)) //6.RM: average number of rooms per dwelling
    .add(StructField("AGE", DoubleType, true)) //7.AGE: proportion of owner-occupied units built prior to 1940
    .add(StructField("DIS", DoubleType, true)) //8.DIS: weighted distances to five Boston employment centres
    .add(StructField("RAD", DoubleType, true)) //9.RAD: index of accessibility to radial highways
    .add(StructField("TAX", DoubleType, true)) //10.TAX: full-value property-tax rate per $10,000
    .add(StructField("PRTATIO", DoubleType, true)) //11.PTRATIO  pupil-teacher ratio by town
    .add(StructField("B", DoubleType, true)) //12.B: 1000(Bk - 0.63)^2 where Bk is the proportion of blacks by town
    .add(StructField("LSTAT", DoubleType, true)) //13.LSTAT: % lower status of the population
    .add(StructField("MEDV", DoubleType, true)) //14.MEDV: Median value of owner-occupied homes in $1000's

  def getNameFromSchema(colName: String): String = {
    var inc = 0
    schema.map(x => {
      inc += 1
      s"col${inc - 1}" -> x.name
    }).toMap.get(colName).getOrElse(colName)

  }


  def main(args: Array[String]): Unit = {
    import org.apache.spark.mllib.linalg.Vectors

    /*
    4.0. Preparación de los datos de entrada
    */
    //val casas = dataExploration()
    val casas = spark.sparkContext.textFile(getClass.getResource("/ml/housing.data").getPath, 6)
    casas.foreach(println)

    println(s"\n************ 4.0: casasValores ************")
    val casasValores = casas.map(x => Vectors.dense(x.split(",").map(_.trim().toDouble)))
    casasValores.foreach(println)

    /*
    4.1. Transformando a puntos etiquetados
    */
    import org.apache.spark.mllib.regression.LabeledPoint
    println(s"\n************ 4.1: casasDatos ************")
    val casasDatos = casasValores.map(x => {
      val a = x.toArray
      LabeledPoint(a(a.length - 1), Vectors.dense(a.slice(0, a.length - 1)))
    })
    //casasDatos.foreach(println)
    val casasDatosDf = spark.createDataFrame(casasDatos)
    casasDatosDf.show(false)

    /*
    5.0. Dividir los datos: entrenamiento y validación
    */
    val sets = casasDatos.randomSplit(Array(0.8, 0.2))
    val casasEntrenamiento = sets(0)
    val casasValidacion = sets(1)

    /*
    5.1. Escalado de características y normalización media
    */
    import org.apache.spark.mllib.feature.StandardScaler
    println(s"\n************ 5.1. Escalado de características y normalización media... ************")
    val scaler = new StandardScaler(true, true).fit(casasEntrenamiento.map(x => x.features))
    println(s"$scaler")
    println(s"\n************ 5.1. entrenamientoEscalado ************")
    val entrenamientoEscalado = casasEntrenamiento.map(x =>
      LabeledPoint(x.label, scaler.transform(x.features)))
    println(s"\n************ 5.1. validacionEscalado ************")
    val validacionEscalado = casasValidacion.map(x =>
      LabeledPoint(x.label, scaler.transform(x.features)))
    //    validacionEscalado.toDF().show(false)
    //

    /*
    6.1. Método de entrenamiento estático
    */
    println(s"\n************ 6.1. Método de entrenamiento estático ************")
    val modelo = LinearRegressionWithSGD.train(entrenamientoEscalado, 200, 1.0)
    println(modelo)


    /*
    6.2.  Método no estándar
    */
    println(s"\n************ 6.2.  Método no estándar ************")
    import org.apache.spark.mllib.regression.LinearRegressionWithSGD
    val alg = new LinearRegressionWithSGD()
    alg.setIntercept(true)
    alg.optimizer.setNumIterations(200)
    entrenamientoEscalado.cache()
    validacionEscalado.cache()

    //  Comienza  el entrenamiento del modelo.!!
    val modelo2 = alg.run(entrenamientoEscalado)



    /*
    7. Predecir los valores objetivo
    */
    println(s"\n************ 7. Predecir los valores objetivo ************")
    val precccionesValidas = validacionEscalado.map(x =>
      (modelo.predict(x.features), x.label))
    precccionesValidas.collect()

    val rmse = math.sqrt(precccionesValidas.map { case (p, l) =>
      math.pow(p - l, 2)
    }.mean())
    println(s"rmse=$rmse")

    /*
    8.  Evaluar el rendimiento del modelo
    */
    println(s"\n************ 8.  Evaluar el rendimiento del modelo  ************")
    import org.apache.spark.mllib.evaluation.RegressionMetrics
    val metricas = new RegressionMetrics(precccionesValidas)
    println(s"metricas.rootMeanSquaredError = ${metricas.rootMeanSquaredError}")
    println(s"metricas.meanSquaredError = ${metricas.meanSquaredError}")

    /*
    9. Interpretando los parámetros del modelo
    */
    println(s"\n************ 9. Interpretando los parámetros del modelo  ************")
    println(modelo.weights.toArray.map(x =>
      x.abs).zipWithIndex.sortBy(_._1).mkString(",  \n"))

  }


}
