package pl.jaca.server.proxy.server

import akka.actor.Actor
import io.netty.channel.Channel
import pl.jaca.server.proxy.packets.OutPacket
import pl.jaca.server.proxy.server.ConnectionProxy.{ForwardPacket, GetChannel, RChannel}

/**
 * @author Jaca777
 *         Created 2015-10-11 at 15
 */
class ConnectionProxy(val channel: Channel) extends Actor {

  override def receive: Receive = {
    case ForwardPacket(packet) => channel.writeAndFlush(packet)
    case GetChannel => sender ! RChannel(channel)
  }


}

object ConnectionProxy {

  //IN
  case class ForwardPacket(outPacket: OutPacket)

  object GetChannel

  //OUT
  case class RChannel(channel: Channel)

}
