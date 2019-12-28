package com.example.studentandroidapp

import com.example.studentandroidapp.models.Student
import com.google.gson.Gson
import org.junit.Test
import org.junit.Assert.assertEquals

class StudentModelTest {

    @Test
    fun testStudentModel() {

        val student = Student(1,"Barry",28)

        assertEquals(1,student.id)
        assertEquals("Barry",student.name)
        assertEquals(28,student.age)

        student.id=2
        student.name="Martha"
        student.age=31

        assertEquals(2,student.id)
        assertEquals("Martha",student.name)
        assertEquals(31,student.age)

        val newStudent = student.copy(id=3,age=22)

        assertEquals(3,newStudent.id)
        assertEquals("Martha",newStudent.name)
        assertEquals(22,newStudent.age)
    }

    @Test
    fun testStudentModelMappingToJson() {

        val gson = Gson()
        val student = Student(1,"Barry",28)
        val json = gson.toJson(student)

        assertEquals("""{"id":1,"name":"Barry","age":28}""",json)
    }

    @Test
    fun testStudentModelMappingFromJson() {

        val gson = Gson()
        val jsonString = """{"id":2,"name":"Sheila","age":33}"""
        val testStudentModel = gson.fromJson(jsonString, Student::class.java)

        assertEquals(testStudentModel.id,2)
        assertEquals(testStudentModel.name,"Sheila")
        assertEquals(testStudentModel.age,33)
    }
}