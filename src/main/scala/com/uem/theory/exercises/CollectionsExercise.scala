package com.uem.theory.exercises

case class TvProgram(channel: Int, duration: Int)

object CollectionsExercise extends App {

  val programs = Seq(TvProgram(1, 60), TvProgram(2, 120), TvProgram(4, 30), TvProgram(5, 130))

  // Select the programs emitted in even channels
  val even = "programs.filter ..."
  println(even)

  // Find the program with the max duration
  val maxDurProgram = "..."
  println(maxDurProgram)
}