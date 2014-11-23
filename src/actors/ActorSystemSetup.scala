package actors

import akka.actor.ActorSystem
import akka.routing.FromConfig
import akka.actor.Props
import java.util.Date
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Await
import scala.concurrent.duration._

object SimpleFileConfiggedRouterSetup extends App {
  val system = ActorSystem("SimpleSystem")
  val simpleRouted = system.actorOf(Props[ListenerWriter].withRouter(FromConfig()),
                                    name = "simpleRoutedActor")
  println(simpleRouted.path)
  
  implicit val timeout:akka.util.Timeout=2000
  
  val actRefFuture=system.actorSelection("/user/simpleRoutedActor").resolveOne()
  
  val actRef = Await.result(actRefFuture, 0.5 seconds)
  
  for (n <- 1 until 10)  
    //simpleRouted ! requestMsg(new Date, "Hello, #%d!".format(n).getBytes)
     system.actorSelection("/user/simpleRoutedActor") ! requestMsg(new Date, "Hello, #%d!".format(n).getBytes)
     //actRef ! requestMsg(new Date, "Hello, #%d!".format(n).getBytes)
    
  Thread.sleep(2000)
  

  
  system.shutdown()
}

