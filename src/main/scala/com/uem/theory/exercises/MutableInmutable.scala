package com.uem.theory.exercises

object MutableInmutable extends App {

  var is = Set(1, 2, 3)
  is += 4
  is -= 2
  println(is)

  val ms = collection.mutable.Set(1, 2, 3)
  ms += 4
  ms -= 2
  println(ms)

}