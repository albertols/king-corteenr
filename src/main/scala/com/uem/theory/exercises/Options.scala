package com.uem.theory.exercises

object Options extends App {

  //Playing with options
  val lleno: Option[String] = Some("alberto")
  val vacio = None
  println(lleno.isEmpty)
  println(lleno.isDefined)
  println(lleno.getOrElse("b"))
  println(vacio.getOrElse("b"))
  println(lleno.get)
  println(vacio.get) //error

  def f_pat[T](o: Option[T]) = {
    o match {
      case None => println("Option is empty")
      case Some(v) => println(s"Option has value: $v")
    }
  }

  f_pat(lleno)
  f_pat(vacio)

}