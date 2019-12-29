package com.example.studentandroidapp.network

import com.example.studentandroidapp.models.Student
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentRestApiService {
    //Get method for coroutines
    @GET("students/{id}")
    suspend fun getStudentById(@Path("id") path: String): Response<Student>
    //Get method for coroutines
    @GET("students")
    suspend fun getAllStudents(): Response<List<Student>>
}