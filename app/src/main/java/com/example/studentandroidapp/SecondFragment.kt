package com.example.studentandroidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.studentandroidapp.models.Student
import com.example.studentandroidapp.network.StudentRestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textview_second).text =
                getString(R.string.hello_second_fragment, args.myArg)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        view.findViewById<Button>(R.id.button_get_student_id_one).setOnClickListener {
            displayStudentName()
        }
    }

    private fun displayStudentName() {
        val service = StudentRestApi.createRetrofitService()
        var response  : Response<Student>? = null
        CoroutineScope(Dispatchers.IO).launch {
            try {
                response = service.getStudentById("1")
            } catch (e: Throwable) {
                print(e.message)
            }
            withContext(Dispatchers.Main) {
                    if (response?.isSuccessful!!) {
                        view?.findViewById<TextView>(R.id.textview_second)?.text =
                            response!!.body().toString()
                    } else {
                        view?.findViewById<TextView>(R.id.textview_second)?.text=getString(R.string.network_error)
                    }
            }
        }
    }
}
