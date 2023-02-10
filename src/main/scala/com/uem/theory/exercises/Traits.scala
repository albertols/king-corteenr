package com.uem.theory.exercises

import com.uem.theory.exercises.factory.PhoneHacker

object Traits extends App {

  // Define and extend traits and abstract classes
  trait Singer {
    def sing(): String

    def tune: String

    var song: String
    var album: String
  }

  trait DataPlumber {
    val apagoFuegos = "apagoFuegos"
  }

  trait BigDataSkills extends DataPlumber {
    def iCodeInScala = "se picar en Scala"

    def iCodeInManyPL(num: Integer) = num * 2

    def iDebugInScala: String // soy abastact

    val MY_FAV_PL = "Python"
  }

  abstract class Person(val name: String) {
    def sayHi: String = s"Hi! I'm $name. " + additionalInfo

    protected def additionalInfo: String
  }

  class Shakira(name: String, override var song: String)
    extends Person(name) with Singer with BigDataSkills {
    override def sing = song

    override def tune = song + "auto_tune_bizarrap"

    override protected def additionalInfo = sing

    override var album: String = "new_album"

    override def toString: String = s"my name is $name=$song + $tune + $additionalInfo + $album"

    override def iDebugInScala: String = "I debug in SCala in UEM"
  }

  val myCasioShakira = new Shakira("Shaky", "untitled")
  println(myCasioShakira)
  println(myCasioShakira.iDebugInScala)
  println(myCasioShakira.apagoFuegos)
  val aSinger = "..." // instantiate SingerPerson
  println("...") // make them sing
  println("...") // make them say hi
  println("...") // print their name
  // change their song
  println("...") // and make them sing again

  class SuperShaky(name: String, song: String)
    extends Shakira(name: String, song: String) with PhoneHacker {

    // strategy design pattern
    def stealPhones = s"he hackeado ${hack(add, 3, 4)} este numero de telefonos"
  }

  val mySuperShaky = new SuperShaky("Shaky", "untitled")
  println (mySuperShaky.stealPhones)
  System.exit(-1)

}