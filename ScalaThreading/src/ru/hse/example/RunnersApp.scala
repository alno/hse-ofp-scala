package ru.hse.example

import scala.concurrent.ops._


object RunnersApp extends App {

  val runner = defaultRunner

  (1 to 5).foreach { ind =>

    runner.execute(runner.functionAsTask(run(ind)))

  }

  def run(ind: Int)() {
    for (i <- 1 to 10) {
      println(ind + i*10)
      Thread.sleep(10)
    }

  }

}
