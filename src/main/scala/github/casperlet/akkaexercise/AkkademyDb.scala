package github.casperlet.akkaexercise

import akka.actor.Status.Failure
import akka.actor.{Actor, Status}
import akka.event.Logging
import github.casperlet.akkaexercise.messages.{GetRequest, KeyNotFoundException, SetRequest}

import scala.collection.mutable

class AkkademyDb extends Actor {
  val map = new mutable.HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SetRequest(key, value) =>
      map.put(key, value)
      log.info(s"received SetRequest - key:{$key} value:{$value}")
      sender() ! Status.Success
    case GetRequest(key) =>
      log.info(s"received get request - key:{$key}")
      val response = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None => sender() ! Failure(KeyNotFoundException(key))
      }
    case o => log.info(s"received unknown messages: {$o}")
      sender() ! Failure(new ClassNotFoundException)
  }
}