package com.uem.theory.exercises

object CaseClasses extends App {

  // Create case class and test
  case class Message(sender: String, body: String)

  val message1 = Message("me", "my message")
  val message2 = Message("me", "my message")

  println(message1 == message2) // value comparison
  println(message1.sender) // public access to fields
  //message1.sender = "new sender" // error: inmutable fields (val)
  println(message1) // toString implementation provided
}