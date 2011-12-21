package ru.hse.example

object ThreadsApp extends App{

  println("Init")

  val threads = (1 to 5).map { ind =>
    new Thread {

      override def run {
        for (i <- 1 to 100) {
          Thread.sleep(10)
          println(ind + i*10)
        }
      }

    }
  }

  println("Start")

  threads.foreach(_.start)

  println("Started")

  threads.foreach(_.join)

  println("Completed")

}
