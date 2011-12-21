package ru.hse.example

import scala.concurrent.ops._
import scala.swing._

object SwingThreadingApp extends SimpleSwingApplication {

  val progress = new ProgressBar {
    min = 0
    max = 100
    value = 0
  }

  def top = new MainFrame {
    contents = new FlowPanel {

      contents += progress
      contents += Button("Start") {
        spawn {
          for (i <- 1 to 100) {
            Swing.onEDT {
              progress.value = i
            }

            Thread.sleep(100)
          }
        }
      }
    }
  }

}
