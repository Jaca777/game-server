package pl.jaca.server.packets

import pl.jaca.server.{Session, Session$}
import pl.jaca.server.networking.{PacketEncoder, ServerEvent}

/**
 * @author Jaca777
 *         Created 2015-06-13 at 13
 */
class InPacket(id: Short, length: Short, msg: Array[Byte], val sender: Session) extends Packet(id, length, msg) with ServerEvent.InPacketEvent

