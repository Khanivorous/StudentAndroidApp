package com.example.studentandroidapp.network

import com.example.studentandroidapp.models.Student
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * This class currently erroneously contains calls using RxJava AND Coroutines
 * This is temporary just to get experiment with both ways of handling data
 * @Todo Remove code for RxJava as will go forward with Coroutines
 */
interface StudentRestApiService {
    //Get method for coroutines
    @GET("students/{id}")
    suspend fun getStudentById(@Path("id") path: String): Response<Student>
    //Get method for coroutines
    @GET("students")
    suspend fun getAllStudents(): Response<List<Student>>
    //Get method for RxJava Todo: Remove this call and stick with Coroutines
    @GET("students/{id}")
    fun getStudentByRxId(@Path("id") path: String): Single<Student>

}