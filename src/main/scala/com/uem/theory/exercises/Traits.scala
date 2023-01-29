package com.uem.theory.exercises

object Traits extends App {

  // Define and extend traits and abstract classes

  trait Singer {
    def sing: String
    var song: String
  }

  abstract class Person(val name: String) {
    def sayHi: String = s"Hi! I'm $name. " + additionalInfo
    protected def additionalInfo: String
  }

  class SingerPerson(name: String, override var song: String)
    extends Person(name) with Singer {
    override def sing = song
    override protected def additionalInfo = sing
  }

  val aSinger = "..." // instantiate SingerPerson
  println("...") // make them sing
  println("...") // make them say hi
  println("...") // print their name
  // change their song
  println("...") // and make them sing again

}