package ru.hse.example

import scala.actors._
import scala.actors.Actor._
import scala.actors.remote._
import scala.actors.remote.RemoteActor._

object RemotePingApp extends App {

  RemoteActor.classLoader = getClass.getClassLoader

  val pinger = actor {
    react {
      case Start(ponger) =>
        ponger ! Ping(0)

        loop {
          react {
            case Pong(num) =>
              println(Pong(num))
              Thread.sleep(1000)
              sender ! Ping(num+1)
          }
        }
    }
  }

  val ponger = select(Node("localhost", 9000), 'ponger)

  pinger ! Start(ponger)
}
