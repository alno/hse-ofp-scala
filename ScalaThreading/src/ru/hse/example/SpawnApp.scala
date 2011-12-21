package ru.hse.example

import scala.concurrent.ops._

object SpawnApp extends App {

  val tasks = (1 to 5).map { ind =>
    future {
      for ( i <- 1 to 10) {
        println(i*10 + ind)
        Thread.sleep(10)
      }

      ind
    }
  }

  println("Started")

  println(tasks.map(_()))

  println("Completed")

  val (r1, r2) = par({Thread.sleep(5000); 10}, {Thread.sleep(7000); 15})

  println(r1)
  println(r2)

}
