package ru.hse.example

class StudentCollection(val all: Set[Student], 
    studentsByFirstName: Map[String, Set[Student]]) {

  def this() = this(Set(), Map())
  
  def +(s: Student) = {
    val newByName = studentsByFirstName + 
      (s.firstName -> (studentsByFirstName.getOrElse(s.firstName, Set()) + s))
      
    new StudentCollection(all + s, newByName)
  }

  def -(s: Student) = {
    val newByName = studentsByFirstName + 
      (s.firstName -> (studentsByFirstName.getOrElse(s.firstName, Set()) - s))

    new StudentCollection(all - s, newByName)
  }
  
  def withFirstName(firstName: String) =
    studentsByFirstName.getOrElse(firstName, Set())

}
