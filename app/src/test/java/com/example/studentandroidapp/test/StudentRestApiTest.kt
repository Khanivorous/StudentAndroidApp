package com.example.studentandroidapp.test

import com.example.studentandroidapp.network.StudentRestApi
import com.example.studentandroidapp.network.StudentRestApiService
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test

class StudentRestApiTest {

    @Test
    fun testCreateService() {
        val service = StudentRestApi.createRetrofitService()
        assertThat(service, instanceOf(StudentRestApiService::class.java))
    }

}