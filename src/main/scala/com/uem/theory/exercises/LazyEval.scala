package com.uem.theory.exercises

class LazyEval {

}

object LazyEval extends App{

  val myVal = 1
  val myVal2 = 2
  lazy val myValLazy = 3 // no se instancia hasta que se llama

  println(s"myVal=$myVal")
  println(s"myVal2=$myVal2")
  println(s"myVal2=$myValLazy")



}
