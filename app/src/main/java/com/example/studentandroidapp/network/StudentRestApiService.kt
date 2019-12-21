package com.example.studentandroidapp.network

import com.example.studentandroidapp.models.Student
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentRestApiService {

    @GET("students/{id}")
    suspend fun getStudentById(@Path("id") path: String): Response<Student>

    @GET("students")
    suspend fun getAllStudents(): Response<List<Student>>

}