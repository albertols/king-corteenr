package com.uem.theory.exercises

object PatternMatching extends App {

  // Pattern match with lists
  def reverse[A](ls: List[A]): List[A] = ls match {
    case Nil => Nil // abencnce value in collections
    case h :: tail => reverse(tail) :+ h
  }
  println(reverse(List(1, 2, 3, 4, 5)))

  // Pattern matching with case classes
  trait Notification // interface w/ vitamins
  case class Email(sender: String, title: String, body: String) extends Notification
  case class SMS(caller: String, message: String) extends Notification
  case class Whatsapp(caller: String, message: String) extends Notification

  def notify(n: Notification) = println(
    n match {
      case Email(sender, title, body) => s"You have new email from $sender: $title | $body"
      case SMS(caller, message) => s"$caller sent you this message: $message"
      case Whatsapp(caller, message) => s"who $caller  $message?"
      case _ => s"Unknown type of notification"
    })

  notify(SMS("Someone", "call me"))
  notify(Email("no one", "nothing", "nothing else"))
  notify(Whatsapp("Called", "me"))


  // Pattern guards
  def notifyImportant(n: Notification) = println(
    n match {
      case Email(sender, _, _) if sender == "important" => s"You have important mail!"
      case Email(sender, _, _) if "not priority".contains(sender) => s"You have a priority e-mail!"
      case SMS(caller, _) if caller == "important" => s"You have important messages!"
      case _ => s"Nothing important"
    })

  println (s"------")
  notifyImportant(Email("priority", "nothing", "nothing else"))
  notifyImportant(Email("important", "nothing", "nothing else"))
  notifyImportant(SMS("Someone", "call me"))


  // TODO Create a new type of notification (Call, with field caller),
  //  and complete the method below to return the origin of the notification for the three known types
  def whoSentThis(n: Notification): String = {
    "..."
  }

  println(whoSentThis(Email("sender1", "title", "body"))) // should print "sender1"
  //println(whoSentThis(Call("me"))) // should print "me"

}