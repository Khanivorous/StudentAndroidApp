package com.example.studentandroidapp.network

import com.example.studentandroidapp.models.Student
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentRestApiService {

    @GET("students/{id}")
    fun getStudentById(@Path("id") path: String): Single<Student>

    @GET("students")
    fun getAllStudents(): Single<List<Student>>

}