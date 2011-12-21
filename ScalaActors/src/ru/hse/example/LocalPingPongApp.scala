package ru.hse.example

import scala.actors._
import scala.actors.Actor._

object LocalPingPongApp extends App {

  case class Start(ponger: Actor)
  case class Ping(num: Int)
  case class Pong(num: Int)

  val pinger = actor {
    react {
      case Start(ponger) =>
        ponger ! Ping(0)

        loop {
          react {
            case Pong(num) =>
              println(Pong(num))
              Thread.sleep(1000)
              sender ! Ping(num + 1)
          }
        }
    }
  }

  val ponger = actor {
    loop {
      react {
        case Ping(num) =>
          println(Ping(num))
          sender ! Pong(num)
      }
    }
  }

  pinger ! Start(ponger)
}
