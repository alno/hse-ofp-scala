package ru.hse.example

import scala.swing._
import scala.io.Source
import java.io.FileWriter

object NotepadApp extends SimpleSwingApplication {

  val textArea = new TextArea

  def top = new MainFrame {

    menuBar = new MenuBar {
      contents += new Menu("File") {
        contents += new MenuItem(Action("New") {
          textArea.text = ""
        })

        contents += new MenuItem(Action("Open") {
          val dialog = new FileChooser

          if (dialog.showOpenDialog(textArea) == FileChooser.Result.Approve)
            textArea.text = Source.fromFile(dialog.selectedFile).mkString
        })

        contents += new MenuItem(Action("Save") {
          val dialog = new FileChooser

          if (dialog.showSaveDialog(textArea) == FileChooser.Result.Approve) {
            val w = new FileWriter(dialog.selectedFile)
            w.write(textArea.text)
            w.close
          }
        })
      }
    }

    contents = new ScrollPane(textArea)

  }

}
