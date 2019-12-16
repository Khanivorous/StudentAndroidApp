package com.example.studentandroidapp.models

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("age") var age: Int
)