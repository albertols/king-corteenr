package com.uem.theory.exercises

import com.uem.theory.exercises.reflection.Julio

/**
 * IMPORTANT:
 * Implicit conversions can be used to extend the functionality of existing classes in a concise and elegant way.
 */
object Implicits extends App {

  //Ej.1: we're adding a method uni to the built-in Scala class String.
  implicit class StringWithSpeak(val s: String) extends Julio (s, 50){
    def uni = s"I am studying: $s"

    override def divideAndWin(): Unit = {
      println("me divido soy Julio")
    }
  }

  println("bases de datos".divideAndWin())

  "Scala".uni
  println("Scala".uni)
  "BigData".uni
  println("BigData".uni)

  // Ej.2: uses an implicit parameter ordering of type Ordering[T].
  // The Ordering trait provides a comparison function for values of type T.
  // The Scala library provides implicit values for many common types, including Int, Double, and String.
  def maxOrderedValue[T](a: T, b: T)(implicit ordering: Ordering[T]): T = {
    if (ordering.compare(a, b) < 0) b else a
  }

  println(maxOrderedValue(3, 5))
  println(maxOrderedValue("uem", "universidad europea"))


}
