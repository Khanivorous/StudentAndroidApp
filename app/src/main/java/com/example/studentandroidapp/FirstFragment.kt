package com.example.studentandroidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.studentandroidapp.models.Student
import com.example.studentandroidapp.network.StudentRestApi
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.HttpException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

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

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
                //displayStudentName()
            displayrxStudentName()

        }
    }

    private fun displayrxStudentName() {
        val service = StudentRestApi.createRetrofitService()
        myCompositeDisposable?.add(service.getStudentByRxId("1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { student -> this.handleRxResults(student) },
                {t -> this.handleRxError(t) }
            ))
    }

    private fun handleRxResults(student: Student) {
        view?.findViewById<TextView>(R.id.textview_first)?.text=student.name
    }

    private fun handleRxError(t: Throwable) {
        view?.findViewById<TextView>(R.id.textview_first)?.text=getString(R.string.network_error)
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
                        view?.findViewById<TextView>(R.id.textview_first)?.text=getString(R.string.network_error)
                    }
                } catch (e: HttpException) {
                    view?.findViewById<TextView>(R.id.textview_first)?.text=e.message
                } catch (e: Throwable) {
                    view?.findViewById<TextView>(R.id.textview_first)?.text=e.message
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
    }
}
