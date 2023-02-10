package com.uem.theory.exercises.exceptions


object ExceptionsExercise2 {
  import scala.util.{Failure, Success, Try}
  def divide(x: Int, y: Int): Try[Int] = {
    if (y == 0) Failure(new ArithmeticException("division by zero"))
    else Success(x / y)
  }

  def main(args: Array[String]): Unit = {
    val result = divide(10, 2)
    result match {
      case Success(value) => println(s"Result of division is: $value")
      case Failure(ex) => println(s"An error occurred: ${ex.getMessage}")
    }

    val result2 = divide(10, 0)
    result2 match {
      case Success(value) => println(s"Result of division is: $value")
      case Failure(ex) => println(s"An error occurred: ${ex.getMessage}")
    }
  }

}
