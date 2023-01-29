package com.uem.theory.exercises

object Objects extends App {

  // Define a utility object
  object Util {
    def sum(l: List[Int]): Int = l.sum
    val constant = "A constant"
  }
  val r = Util.sum(List(1, 2, 3))
  println(Util.constant)

  // Define a companion object for static members of the companion class
  class X(var x: Int) {
    import X._
    def increment(): Unit = {
      x = x + Constant
    }
  }
  object X {
    private val Constant = 10
  }

  val x = new X(5)
  x.increment()
  println(x.x)
  // println(X.Constant) //value Constant in object X cannot be accessed in object com.pragsis.master.scala.Objects

}