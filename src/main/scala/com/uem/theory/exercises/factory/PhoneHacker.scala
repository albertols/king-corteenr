package com.uem.theory.exercises.factory

trait PhoneHacker {


  def add(a: Int, b: Int) = a + b
  def subtract(a: Int, b: Int) = a - b
  def multiply(a: Int, b: Int) = a * b

  def hack(callback: (Int, Int) => Int, x: Int, y: Int) = callback(x, y)

  //println("Add:      " + hack(add, 3, 4))
  //println("Subtract: " + hack(subtract, 3, 4))
  //println("Multiply: " + hack(multiply, 3, 4))

}
