package com.example.apidog.model

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
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
    private var selectedImage = MutableLiveData<ListDog>()

    fun selectedImage() = selectedImage

    fun updateList(list: List<ListDog>){
        listListDog = list
        notifyDataSetChanged()
    }

    inner class ListDogViewHolder(private val binding: ItemImageviewBinding):
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {
        fun bind(listDog: ListDog){
            Glide.with(binding.imageView)
                    .load(listDog.list_dog)
                    .centerCrop()
                    .into(binding.imageView)
            if (listDog.favorites){
                binding.iconLike.setColorFilter(Color.RED)
            } else {
                binding.iconLike.clearColorFilter()
            }
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean {
            selectedImage.value = listListDog[adapterPosition]
            return true
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