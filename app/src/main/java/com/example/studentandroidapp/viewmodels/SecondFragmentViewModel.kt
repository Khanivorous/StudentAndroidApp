package com.example.studentandroidapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studentandroidapp.datamodels.Student
import com.example.studentandroidapp.network.StudentRestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class SecondFragmentViewModel : ViewModel() {

    private  val _name = MutableLiveData<String>()
    val name : LiveData<String>
        get() = _name

    init {
        _name.value = "Students name will go here"
    }
    /**
     * In this fragment I'm experimenting with Coroutines to handle the network call
     * @Todo Write UI tests
     */

    fun getSecondStudentsName() {
        val service = StudentRestApi.createRetrofitService()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.getStudentById("2")
                }
                handleResults(response)
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun handleResults(student: Response<Student>) {
        _name.value = student.body()?.name ?: "Name does not exist"
    }

    private fun handleError(error: Throwable) {
        Log.e("NetworkError", error.message.toString())
        _name.value = "OOPS Network error"
    }
}