package com.example.apidog.model.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apidog.databinding.FragmentSecondBinding
import com.example.apidog.model.adapter.ListDogAdapter
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
        var adapter = ListDogAdapter()
        binding.rv2.adapter = adapter
        binding.rv2.layoutManager = LinearLayoutManager(context)
        binding.tvTitulo.text = bunBreed.toUpperCase()
        viewModel.returnImage(bunBreed).observe(viewLifecycleOwner, Observer {
            it?.runCatching {
                adapter.updateList(it)
            }
        })
    }
}