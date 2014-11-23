package actors

import akka.actor.Actor
import akka.actor.ActorLogging

case class requestMsg(receivedTime:java.util.Date, msg:Array[Byte])

class ListenerWriter extends Actor with ActorLogging{
  def receive = {
    case requestMsg(receivedTime,msg) =>
      println("time: " + receivedTime.getTime.toString + " msg: " + msg.toString())
    case _ =>
      log.info("unrecognized msg: ")
      
  }    
}