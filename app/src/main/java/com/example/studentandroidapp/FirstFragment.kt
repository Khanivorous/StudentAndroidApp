package com.example.studentandroidapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studentandroidapp.models.Student
import com.example.studentandroidapp.network.StudentRestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

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

        view.findViewById<Button>(R.id.button_next).setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment("From FirstFragment")
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.button_get_student_id_one).setOnClickListener {
            displayStudentName("1")
        }
    }

    /**
     * In this fragment I'm experimenting with Coroutines to handle the network call
     * @Todo Write UI tests
     */

    private fun displayStudentName(id: String) {
        val service = StudentRestApi.createRetrofitService()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.getStudentById(id)
                }
                handleResults(response)
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun handleResults(student: Response<Student>) {
        view?.findViewById<TextView>(R.id.textview_first)?.text= student.body()?.name
    }

    private fun handleError(t: Throwable) {
        println(t.message)
        view?.findViewById<TextView>(R.id.textview_first)?.text=getString(R.string.network_error)
    }

}
