package com.example.studentandroidapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel

class FirstViewModel: ViewModel() {
    init {
        Log.i("FirstViewModel","FirstViewModel created!" )
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("FirstViewModel", "FirstViewModel destroyed!")
    }
}