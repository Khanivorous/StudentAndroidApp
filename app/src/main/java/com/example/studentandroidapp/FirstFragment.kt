package com.example.studentandroidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.studentandroidapp.models.Student
import com.example.studentandroidapp.network.StudentRestApi
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var myCompositeDisposable: CompositeDisposable? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myCompositeDisposable = CompositeDisposable()
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_next).setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment("From FirstFragment")
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.button_get_student_id_one).setOnClickListener {
            displayStudentName()
        }
    }

    private fun displayStudentName() {
        val service = StudentRestApi.createRetrofitService()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.getStudentById("1")
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

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
    }
}
