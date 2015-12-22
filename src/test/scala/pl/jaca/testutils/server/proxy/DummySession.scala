package pl.jaca.testutils.server.proxy

import akka.actor.ActorRef
import pl.jaca.server.{Session, Session$}

/**
 * @author Jaca777
 *         Created 2015-12-11 at 18
 */
class DummySession(host: String, proxy: ActorRef) extends Session(host, 1, new DummyNettyChannel(1, 1), proxy){
  def this(host: String) = this(host, ActorRef.noSender)
  def this(proxy: ActorRef) = this("dummyhost", proxy)
}
