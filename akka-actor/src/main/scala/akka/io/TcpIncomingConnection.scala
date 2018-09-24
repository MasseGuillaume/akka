/**
 * Copyright (C) 2009-2018 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.io

import java.nio.channels.SocketChannel
import scala.collection.immutable
import akka.actor.ActorRef
import akka.io.Inet.SocketOption
import scala.collection.immutable

/**
 * An actor handling the connection state machine for an incoming, already connected
 * SocketChannel.
 *
 * INTERNAL API
 */
private[io] class TcpIncomingConnection(
  _tcp:           TcpExt,
  _channel:       SocketChannel,
  registry:       ChannelRegistry,
  bindHandler:    ActorRef,
  options:        immutable.Iterable[SocketOption],
  readThrottling: Boolean)
  extends TcpConnection(_tcp, _channel, readThrottling) {

  signDeathPact(bindHandler)

  registry.register(channel, initialOps = 0)

  def receive = {
    case registration: ChannelRegistration ⇒ completeConnect(registration, bindHandler, options)
  }
}
