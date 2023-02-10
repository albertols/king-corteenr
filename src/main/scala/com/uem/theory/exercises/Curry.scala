package com.uem.theory.exercises

object Curry extends App{

  def simpleMultiply(a: Int, b: Int): Int = a * b

  def curriedMultiply(a: Int)(b: Int): Int = a * b

  val result = curriedMultiply(2)(10)
  println (result)

  // partially applied function (e.g: REPL)
  // https://alvinalexander.com/scala/fp-book/partially-applied-functions-currying-in-scala/
  val result1 = curriedMultiply(2) _
  println(s"estoy paricalmente aplicado 1." + result1)

  val result2 = result1(10)
  println (result2)

  def product(f: Int => Int)(a: Int, b: Int): Int =
    if(a > b) 1
    else {
      f(a) * product(f)(a + 1, b)
    }
  val res3 = product(x => x * x)(3 , 5)
  println (res3)

  val fn = product(x => x * x)_ // only the first parameter list is applied
  println(s"estoy paricalmente aplicado 2."+fn)
  val res4 = fn(3, 5)  // fn is a function which you can pass the second list
  println(res4)






}
