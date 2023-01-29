package com.uem.theory.exercises

object Tuples extends App {

  // Playing with tuples

  val t = (1, "two", List("a", "b"))
  println(t._1)
  println(t._2)
  println(t._3)

  val (first, second, _) = t
  println(first)
  println(second)

}