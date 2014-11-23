package scalaTest

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
 
class HelloActor(myName:String) extends Actor {
  def receive = {
    case "hello" => println("hello from %s".format(myName))
    case _       => println("huh?")
  }
}
 
object Main extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  //val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  val helloActor = system.actorOf(Props(new HelloActor("Tom Cruise")), name = "helloactor")
  helloActor ! "hello"
  helloActor ! "Whats up"
  
  Thread.sleep(2000)
  println(helloActor.path)
  system.shutdown()
  
}