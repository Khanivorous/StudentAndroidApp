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
import com.example.studentandroidapp.network.StudentRestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    /**
     * In this fragment I'm experimenting with Coroutines to handle the network call
     * @Todo Put this logic in a veiwModel
     */
    private fun displayStudentName() {
        val service = StudentRestApi.createRetrofitService()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.getStudentById("1")
                }
                view?.findViewById<TextView>(R.id.textview_second)?.text=response.body().toString()
            } catch (e: Exception) {
                view?.findViewById<TextView>(R.id.textview_second)?.text=getString(R.string.network_error)
            }
        }
    }
}

