package ru.hse.example

import scala.actors._
import scala.actors.Actor._
import scala.actors.remote._
import scala.actors.remote.RemoteActor._

object PongApp extends App {

  RemoteActor.classLoader = getClass.getClassLoader

  actor {
    alive(9000)
    register('ponger, self)

    loop {
      react {
        case Ping(num) =>
          println(Ping(num))
          sender ! Pong(num)
      }
    }
  }

}
