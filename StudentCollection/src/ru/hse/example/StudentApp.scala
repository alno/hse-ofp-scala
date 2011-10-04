package ru.hse.example

object StudentApp extends App {

  def readStudent = {
    println("Enter student data(firstName,lastName,age):")
    Student(readLine, readLine, readInt)
  }

  var collection = new StudentCollection()

  while (true) {
    print("> ")

    val cmd = readLine

    if (cmd == "new") {
      collection = collection + readStudent
    } else if (cmd == "del") {
      collection = collection - readStudent
    } else if (cmd == "all") {
      println(collection.all)
    } else if (cmd == "byname") {
      println(collection withFirstName readLine)
    } else {
      println("Unknown command")
    }
  }
}
