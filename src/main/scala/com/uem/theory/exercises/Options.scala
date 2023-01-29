package com.uem.theory.exercises

object Options extends App {

  //Playing with options

  val s: Option[String] = Some("a")
  val n = None
  println(s.isEmpty)
  println(s.isDefined)
  println(s.getOrElse("b"))
  println(n.getOrElse("b"))
  println(s.get)
  println(n.get) //error

  def f[T](o: Option[T]) = {
    o match {
      case None => println("Option is empty")
      case Some(v) => println(s"Option has value: $v")
    }
  }

  f(s)
  f(n)

}