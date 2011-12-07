package ru.hse.example

import java.io._
import scala.io.Source

object IOApp extends App {

  val binOut = new FileOutputStream("file.dat")

  binOut.write(1);
  binOut.write(2);
  binOut.write(5);
  binOut.close()

  val binIn = new FileInputStream("file.dat")
  println(binIn.read())
  println(binIn.read())
  println(binIn.read())

  // new FileWriter("file.txt")
  val textOut = new OutputStreamWriter(new FileOutputStream("file.txt"), "UTF-8")
  textOut.write("тест\nдругая строка")
  textOut.close()

  // new BufferedReader( new FileReader("file.txt"))
  val textIn = new BufferedReader( new InputStreamReader(new FileInputStream("file.txt"), "UTF-8") )

  var line = textIn.readLine()
  while(line != null) {
    println(line)
    line = textIn.readLine()
  }

  val textIn2 = Source.fromFile("file.txt", "UTF-8")

  textIn2.getLines foreach println
}
