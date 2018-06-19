package github.casperlet.akkaexercise.messages

case class KeyNotFoundException(key: String) extends Exception {}
