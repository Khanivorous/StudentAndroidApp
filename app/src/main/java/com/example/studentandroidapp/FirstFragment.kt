package com.example.studentandroidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.studentandroidapp.network.StudentRestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            displayStudentName()
        }
    }

    private fun displayStudentName() {
        val service = StudentRestApi.createRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getStudentById("1")
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        view?.findViewById<TextView>(R.id.textview_first)?.text=response.body().toString()
                    } else {
                        view?.findViewById<TextView>(R.id.textview_first)?.text=getString(R.string.fuck_it)
                    }
                } catch (e: HttpException) {
                    view?.findViewById<TextView>(R.id.textview_first)?.text=e.message
                } catch (e: Throwable) {
                    view?.findViewById<TextView>(R.id.textview_first)?.text=e.message
                }
            }
        }
    }
}
