package github.casperlet.akkaexercise

import akka.actor.Actor
import akka.event.Logging
import github.casperlet.akkaexercise.messages.SetRequest

import scala.collection.mutable

class AkkademyDb extends Actor {
  val map = new mutable.HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SetRequest(key, value) =>
      map.put(key, value)
      log.info(s"received SetRequest - key:{$key} value:{$value}")
    case o => log.info(s"received unknown messages: {$o}")
  }
}