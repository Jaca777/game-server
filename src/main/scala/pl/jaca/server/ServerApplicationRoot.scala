package pl.jaca.server

import akka.actor.{ActorRef, Props}
import pl.jaca.cluster.Application.Launch
import pl.jaca.cluster.distribution.Distribution
import pl.jaca.cluster.{Application, Configurable, SystemNode}
import pl.jaca.server.ServerApplicationRoot.Shutdown
import pl.jaca.server.networking.Server

/**
 * @author Jaca777
 *         Created 2015-06-15 at 21
 * ServerApplicationRoot is responsible for constructing and launching server. Creates services and handlers.
 *    
 */
class ServerApplicationRoot extends Application with Distribution with Configurable{
  implicit val serverAppPath = "server-app"


  val resolverProvider = new PacketResolverProvider(systemConfig)
  val serviceProvider = new ServiceProvider(systemConfig)
  val handlerProvider = new EventHandlerProvider(systemConfig, serviceProvider)
  
  /**
   * Awaiting command to launch.
   */
  override def receive: Receive = {
    case Launch =>
      val server = launch()
      context become running(server)
  }
  
  def running(server: ActorRef): Receive = {
    case Shutdown =>
      server ! Server.Stop
      context.parent ! SystemNode.Shutdown
  }

  /**
   * Launches both server and client application.
   */
  def launch() = {
    val port = getPort
    val resolver = resolverProvider.getResolver
    val handlers = handlerProvider.getActorFactories
    val server = context.actorOf(Props(new Server(port, resolver)))
    server
  }


  def getPort: Int =
    systemConfig.intAt("port").getOrElse(ServerApplicationRoot.defaultPort)
  

}

object ServerApplicationRoot {
  val defaultPort = 81154

  //IN
  object Shutdown

}