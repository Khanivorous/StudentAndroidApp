package com.example.studentandroidapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.studentandroidapp.viewmodels.FirstFragmentViewModel
import com.example.studentandroidapp.R
import com.example.studentandroidapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var viewModel: FirstFragmentViewModel

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        // Inflate view and obtain an instance of the binding class
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first,
            container,
            false
        )

        Log.i("FirstFragment", "Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)
        binding.firstViewModel = viewModel

        /** Setting up LiveData  observation relationship**/
        viewModel.name.observe(this.viewLifecycleOwner, Observer { studentName ->
            binding.textviewFirst.text = studentName
        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment("From FirstFragment")
            findNavController().navigate(action)
        }

    }



}
