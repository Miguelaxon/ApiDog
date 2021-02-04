package com.example.apidog.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.apidog.databinding.ItemBreedBinding

class BreedDogAdapter: RecyclerView.Adapter<BreedDogAdapter.BreedDogViewHolder>() {
    private var listBreedDog = listOf<BreedDog>()
    private var listListDog = listOf<ListDog>()
    private var selectedBreed = MutableLiveData<BreedDog>()
    private var selectedList = MutableLiveData<ListDog>()

    fun selectedBreed(): LiveData<BreedDog> = selectedBreed
    fun selectedList(): LiveData<ListDog> = selectedList

    fun updateBreed(list: List<BreedDog>){
        listBreedDog = list
    }

    fun updateList(list: List<ListDog>){
        listListDog = list
    }

    inner class BreedDogViewHolder(private val binding: ItemBreedBinding):
            RecyclerView.ViewHolder(binding.root),View.OnClickListener {
        fun bind(breedDog: BreedDog){
            binding.tvBreedDog.text = breedDog.breed_dog.toString()
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedBreed.value = listBreedDog[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedDogViewHolder {
        return BreedDogViewHolder(ItemBreedBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BreedDogViewHolder, position: Int) {
        val breedDog = listBreedDog[position]
        holder.bind(breedDog)
    }

    override fun getItemCount(): Int = listBreedDog.size
}