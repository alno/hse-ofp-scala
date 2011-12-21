package ru.hse.example

import scala.actors._

case class Start(ponger: OutputChannel[Any])
case class Ping(num: Int)
case class Pong(num: Int)
