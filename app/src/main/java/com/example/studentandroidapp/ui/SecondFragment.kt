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
import com.example.studentandroidapp.viewmodels.SecondFragmentViewModel
import com.example.studentandroidapp.R
import com.example.studentandroidapp.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var viewModel: SecondFragmentViewModel

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_second,
            container,
            false
        )

        //Use ViewModelProvider to give 1 instance of the viewmodel to the fragment
        // to be used throughout the fragments lifecycle
        Log.i("SecondFragment", "Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)

        //adds viewModel to databinding in layout xml
        binding.secondViewModel = viewModel
        //adds LiveData to databinding in layout xml
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

}

