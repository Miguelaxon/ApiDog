package com.example.apidog.model

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apidog.databinding.ItemBreedBinding
import com.example.apidog.databinding.ItemImageviewBinding

class ListDogAdapter: RecyclerView.Adapter<ListDogAdapter.ListDogViewHolder>() {
    private var listListDog = listOf<ListDog>()
    private var selectedList = MutableLiveData<ListDog>()

    fun selectedList(): LiveData<ListDog> = selectedList

    fun updateList(list: List<ListDog>){
        listListDog = list
        notifyDataSetChanged()
    }

    inner class ListDogViewHolder(private val binding: ItemImageviewBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(listDog: ListDog){
            Log.d("url", listDog.list_dog)
            Glide.with(binding.imageView).load(listDog.list_dog).into(binding.imageView)
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedList.value = listListDog[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDogViewHolder {
        return ListDogViewHolder(ItemImageviewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ListDogViewHolder, position: Int) {
        val listDog = listListDog[position]
        holder.bind(listDog)
    }

    override fun getItemCount(): Int = listListDog.size
}