package com.uem.theory.exercises.exceptions
import java.io.FileNotFoundException

object ExceptionsExercise {

  def main(args: Array[String]): Unit = {
    try {
      val file = scala.io.Source.fromFile("file.txt")
      println(file.getLines.mkString)
    } catch {
      case ex: FileNotFoundException => println(s"File not found: ${ex.getMessage}")
      case ex: Throwable => println(s"An unexpected error occurred: ${ex.getMessage}")
    } finally {
      println("Closing the file.")
    }
  }

}
