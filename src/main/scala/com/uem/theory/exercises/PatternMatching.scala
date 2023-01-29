package com.uem.theory.exercises

object PatternMatching extends App {

  // Pattern match with lists
  def reverse[A](ls: List[A]): List[A] = ls match {
    case Nil => Nil
    case h :: tail => reverse(tail) :+ h
  }

  println(reverse(List(1, 2, 3, 4, 5)))

  // Pattern matching with case classes
  trait Notification

  case class Email(sender: String, title: String, body: String) extends Notification
  case class SMS(caller: String, message: String) extends Notification

  def notify(n: Notification) = println(
    n match {
      case Email(sender, title, body) => s"You have new email from $sender: $title | $body"
      case SMS(caller, message) => s"$caller sent you this message: $message"
      case _ => s"Unknown type of notification"
    })

  notify(SMS("Someone", "call me"))
  notify(Email("no one", "nothing", "nothing else"))

  // Pattern guards

  def notifyImportant(n: Notification) = println(
    n match {
      case Email(sender, _, _) if sender == "important" => s"You have important mail!"
      case SMS(caller, _) if caller == "important" => s"You have important messages!"
      case _ => s"Nothing important"
    })

  notifyImportant(SMS("Someone", "call me"))
  notifyImportant(Email("important", "nothing", "nothing else"))

  // TODO Create a new type of notification (Call, with field caller), and complete the method below to return the origin of the notification for the three known types

  def whoSentThis(n: Notification): String = {
    "..."
  }

  println(whoSentThis(Email("sender1", "title", "body"))) // should print "sender1"
  //println(whoSentThis(Call("me"))) // should print "me"

}