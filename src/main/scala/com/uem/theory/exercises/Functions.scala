package com.uem.theory.exercises

object Functions extends App {

  // Several ways to define the same function
  val f: Int => Int = i => i + 1
  //val f = (i: Int) => i+1
  //val f: Int => Int = _+1

  println(f(1))

  // And a equivalent method
  def m(i: Int) = i + 1
  //def m(i:Int):Int = i+1
  //def m(i:Int):Int  = f(i)
  //def m = f

  println(m(1))

  // Testing the apply method
  object Greet {
    def apply(name: String) = println(s"Hi $name!")
  }
  Greet("you")
  Greet.apply("stranger")

  // Non-parameter functions
  def f1 = 1
  def f2() = 2
  println(f1)
  //println(f1()) // error: Int does not take parameters
  println(f2)
  println(f2())

  // Currying
  def filter(xs: List[Int], p: Int => Boolean): List[Int] =
    if (xs.isEmpty) xs
    else if (p(xs.head)) xs.head :: filter(xs.tail, p)
    else filter(xs.tail, p)

  def modN(n: Int)(x: Int) = ((x % n) == 0)

  val nums = List(1, 2, 3, 4, 5, 6, 7, 8)
  println(filter(nums, modN(2)))
  println(filter(nums, modN(3)))

}