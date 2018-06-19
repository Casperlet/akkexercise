package github.casperlet.akkaexercise

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import github.casperlet.akkaexercise.messages.SetRequest
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._

class AkkademyDbSpec extends FunSpecLike with Matchers {
  implicit val system: ActorSystem = ActorSystem()
  implicit val timeout: Timeout = Timeout(5 seconds)

  describe("akkademyDb") {
    describe("given set request") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value")

        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value"))
      }
    }
  }
}
