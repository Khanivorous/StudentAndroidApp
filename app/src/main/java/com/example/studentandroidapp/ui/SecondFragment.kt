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
import androidx.navigation.fragment.navArgs
import com.example.studentandroidapp.R
import com.example.studentandroidapp.databinding.FragmentSecondBinding
import com.example.studentandroidapp.viewmodels.SecondFragmentViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val args: SecondFragmentArgs by navArgs()

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

        Log.i("SecondFragment", "Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(SecondFragmentViewModel::class.java)

        /** Setting up LiveData  observation relationship**/
        viewModel.name.observe(this.viewLifecycleOwner, Observer { studentName ->
            binding.textviewSecond.text = studentName
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGetStudentIdTwo.setOnClickListener {
            viewModel.getSecondStudentsName()
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

}

