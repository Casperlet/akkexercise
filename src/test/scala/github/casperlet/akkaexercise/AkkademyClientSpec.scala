package github.casperlet.akkaexercise

import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

class AkkademyClientSpec extends FunSpecLike with Matchers {
  val client: AkkademyClient = new AkkademyClient("127.0.0.1:2552")

  describe("akkademyDb Client") {
    it("should set a value") {
      client.set("123", new Integer(123))
      val future = client.get("123")
      val result = Await.result(future, 10 seconds)
      result should equal(123)
    }
  }
}
