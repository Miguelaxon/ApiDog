package com.example.apidog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.apidog.databinding.FragmentSecondBinding
import com.example.apidog.model.ListDogAdapter
import com.example.apidog.model.viewmodel.ApiDogViewModel

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ApiDogViewModel by activityViewModels()
    private var bunBreed: String = ""

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bunBreed = it.getString("breed","")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ListDogAdapter()
        binding.rv2.adapter = adapter
        binding.rv2.layoutManager = LinearLayoutManager(context)
        viewModel.returnImage(bunBreed).observe(viewLifecycleOwner, Observer {
            it?.let {
                //Log.d("image","$it")
                //Glide.with(binding.imageView2).load(it[0].list_dog).into(binding.imageView2)
                adapter.updateList(it)
            }
        })
    }
}