package pl.jaca.cluster.distribution

import akka.actor.Actor

/**
 * @author Jaca777
 *         Created 2015-10-04 at 18
 */
trait Distributable extends Actor {
  def getLoad: Load = AbsoluteLoad(1.0f) //TODO Remove it, find other way
}
