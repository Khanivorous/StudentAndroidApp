package com.example.studentandroidapp

import com.example.studentandroidapp.models.Student
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
}