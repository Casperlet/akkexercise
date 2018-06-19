package github.casperlet.akkaexercise

import akka.actor.{ActorSelection, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import github.casperlet.akkaexercise.messages.{GetRequest, SetRequest}

import scala.concurrent.Future
import scala.concurrent.duration._

class AkkademyClient(remoteAddress: String) {
  private implicit val timeout: Timeout = Timeout(2 seconds)
  private implicit val system: ActorSystem = ActorSystem("LocalSystem")

  private val remoteDb: ActorSelection = system.actorSelection(s"akka.tcp://akkademy@$remoteAddress/user/akkademy-db")

  def set(key: String, value: Object): Future[Any] = {
    remoteDb ? SetRequest(key, value)
  }

  def get(key: String): Future[Any] = {
    remoteDb ? GetRequest(key)
  }
}
