package ru.hse.example

import scala.swing._
import scala.swing.event._
import java.awt.{ Color, BasicStroke }

object GraphicsApp extends SimpleSwingApplication {

  val drawingPanel = new Panel {

    var centerX = 0
    var centerY = 100

    var lastMouseX = 0
    var lastMouseY = 0

    override def paint(g: Graphics2D) {
      g.setPaint(Color.black)

      val bounds = g.getClipBounds()
      val xStep = 10
      var x = bounds.x

      g.clearRect(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height)

      while (x < bounds.x + bounds.width) {
        g.drawLine(x, centerY - functionCombo.selection.item(x - centerX), x + xStep, centerY - functionCombo.selection.item(x + xStep - centerX))

        x = x + xStep
      }
    }

    listenTo(mouse.clicks, mouse.moves)

    reactions += {
      case e: MousePressed =>
        lastMouseX = e.point.x
        lastMouseY = e.point.y
      case e: MouseDragged =>
        centerX += e.point.x - lastMouseX
        centerY += e.point.y - lastMouseY

        repaint()

        lastMouseX = e.point.x
        lastMouseY = e.point.y
    }
  }

  def namedFun(name: String, f: Double => Double) = new (Int => Int) {

    override def toString = name

    def apply(x: Int) = Math.round(f(x / 100.0) * 100).toInt

  }

  val functions = List(
    namedFun("Sin", Math.sin ),
    namedFun("Sqrt", Math.sqrt ))

  val functionCombo = new ComboBox[Int => Int](functions)

  def top = new MainFrame {

    contents = new BoxPanel(Orientation.Vertical) {
      contents += drawingPanel
      contents += functionCombo

      listenTo(functionCombo.selection)

      reactions += {
        case e: SelectionChanged =>
          drawingPanel.repaint()
      }
    }

  }

}
