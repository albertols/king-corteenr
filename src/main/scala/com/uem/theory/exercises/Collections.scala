package com.uem.theory.exercises

object Collections extends App {

  // Creating mutable and inmutable collections

  import scala.collection.mutable
  val is = Set(1, 2, 3)
  val ms = mutable.Set(1, 2, 3)

  val is2 = is - 1
  ms += 4

  println(is)
  println(is2)
  println(ms)

  // Creating and using maps

  val map1 = Map("one" -> 1, "two" -> 2, "three" -> 3) //Map of type Map[String, Int]
  val map2 = Map(1 -> "one", "2" -> 2.0, 3.0 -> false) //Map of type Map[Any, Any]

  val element = map1("one")
  println(element)
  println(map1 + ("four" -> 4))
  println(map1 updated ("four", 4))
  println(map1) //map1 hasn't changed

  //three different ways to generate a new map adding 1 to each value
  val map1Transformed1 = map1.map { case (k, v) => (k, v + 1) }
  val map1Transformed2 = map1.map(kv => (kv._1, kv._2 + 1))
  val map1Transformed3 = map1.mapValues(_ + 1)

  println(map1Transformed1 equals map1Transformed2)
  println(map1Transformed2 equals map1Transformed3)

  // TODO Complete the following method to print the value associated to the given key (consider only String to Int maps)

  //def printValue(map: ..., key: ...): Unit = {
  //    ...
  //}
  //
  //printValue(map1,"one")  // should print: one is: 1
  //printValue(map1,"four") // should print: Nothing for four

  // Creating and using seqs

  val seq1 = Seq(1, 2, 3, 4)
  val seq2 = Seq(("one", 1), ("two", 2), ("three", 3))

  // TODO Retrieve first element from seq1
  val element1 = "..."
  println(element1)

  // TODO Find the first index of 2 in seq1
  println("...")

  // TODO Convert seq2 to a map and test if it's equal to map1
  val mapFromSeq = "..."
  println(mapFromSeq == map1)

  // TODO Create a new mutable seq adding 1 to the elements of seq1 and change the second element to 10

  //val seqM: mutable.Seq[Int] = ...
  //...
  //println(seqM)

  // Creating and using lists
  val list1 = List(1, 2, 3) // or  1 :: 2 :: 3 :: Nil

  val listConcatReverse = list1 ++ list1.reverse // or list1 ::: list1.reverse
  println(listConcatReverse)
  println(listConcatReverse.contains(3))

  // TODO Create a map from list1, where keys are the indexes of the elements in the list
  val mapFromList = "..."
  println(mapFromList)

}