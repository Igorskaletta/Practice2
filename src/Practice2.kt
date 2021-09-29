fun main(){
    var persons = mutableListOf(Student("Игорь Коршев", 2000, 5.0, true),
        Student("Александр Иванов", 2001, 3.7),
        Student("Петр Долматов", 2002, 3.25, true),
        Student("Олеся Федина", 2000, 4.2),
        Student("Кирилл Петров", 2003, 4.3),
        Lecturer("Фёдор Петрович", 1972, "Доктор экономических наук", 2009),
        Lecturer("Иван Анатольевич", 1973, "Доктор физико-математических наук", 2010),
        Lecturer("Мария Александровна", 1982, "Кандидат технических наук", 2008),
        Lecturer("Михаил Иванович", 1978, "Кандидат физико-математических наук", 1997),
        Lecturer("Ольга Михайловна", 1986, "Кандидат филологических наук", 2013))

    println("sortByAge:")
    for(item in persons.sortByAge())
        println("name: ${item.name}; age: ${item.age}")

    var students = mutableListOf<Student>()

    persons.forEach { if (it is Student) students.add(it) }

    println("sortByNameStudents:")
    for(item in students.sortByNameStudents())
        println("name: ${item.name}")

    println("sortByAverageGrade:")
    for(item in students.sortByAverageGrade(true))
        println("name: ${item.name}; averageGrade: ${item.averageGrade}")

}

open class Person(var name: String, var birthYear: Int){
    init{
        println("Person is created $name")
    }

    val age = 2021 - birthYear
}

class Student(name: String, birthYear: Int, var averageGrade: Double, var extramural: Boolean = false): Person(name,birthYear){

}

class Lecturer(name: String, birthYear: Int, var degree: String, var experienceFrom: Int): Person(name,birthYear){

}

fun List<Person>.sortByAge(): List<Person> = this.sortedByDescending { it.age }

fun List<Student>.sortByNameStudents(): List<Student> = this.sortedByDescending { it.name }

fun List<Student>.sortByAverageGrade(exceptExtramural: Boolean): List<Student>{
    var new = mutableListOf<Student>()
    return if(!exceptExtramural)
        this.sortedByDescending { it.averageGrade }
    else
        this.filter{!it.extramural}.sortedByDescending { it.averageGrade }
}