package com.example.apidog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apidog.databinding.FragmentFirstBinding
import com.example.apidog.model.BreedDog
import com.example.apidog.model.BreedDogAdapter
import com.example.apidog.model.viewmodel.ApiDogViewModel

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: ApiDogViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = BreedDogAdapter()
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(context)

        viewModel.allDataBreed.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateBreed(it)
            }
        })

        adapter.selectedBreed().observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.selectedBreed(it)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        })
    }
}