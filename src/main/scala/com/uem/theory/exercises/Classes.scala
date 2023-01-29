package com.uem.theory.exercises

object Classes extends App {

  // Define, instantiate and access members of a class
  class SimplePoint(var x: Int, var y: Int) {
    def move(dx: Int, dy: Int): Unit = {
      x = x + dx
      y = y + dy
    }
    override def toString: String = s"($x, $y)"
  }

  val point1 = new SimplePoint(2, 3)
  println(point1.x)
  println(point1)

  // Define private fields and getters/setters
  class Point(private var _x: Int = 0, private var _y: Int = 0) {
    private val bound = 100
    def x = _x
    def x_=(newValue: Int): Unit = {
      if (newValue < bound) _x = newValue else printWarning
    }
    def y = _y
    def y_=(newValue: Int): Unit = {
      if (newValue < bound) _y = newValue else printWarning
    }
    private def printWarning = println("WARNING: Out of bounds")
    override def toString: String = s"($x, $y)"
  }

  val point2 = new Point
  println(point2)
  point2.x = 99
  println(point2)
  point2.x = 101
  //point2._x = 101 //variable _x is private

  // Define additional constructors
  class Greeter(private var _message: String, secondaryMessage: String) {
    def this(message: String) = this(message, "")
    def sayHi() = println(_message + secondaryMessage)
    def message = _message
    def message_=(newMessage: String) = _message = newMessage
  }
  new Greeter("Hello world!").sayHi()
  new Greeter("Hello world!", " I'm a bit more chatty.").sayHi
  val g = new Greeter("Hello world!", " I'm a bit more chatty.")
  g.message = "bye"
  g.sayHi()

  // TODO Make message in class Greeter private and provide getters/setters

}