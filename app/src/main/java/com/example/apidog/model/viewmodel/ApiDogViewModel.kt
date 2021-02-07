package com.example.apidog.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apidog.model.BreedDog
import com.example.apidog.model.ListDog
import com.example.apidog.model.dao.ApiDogBD
import com.example.apidog.model.remote.ApiDogRepository
import kotlinx.coroutines.launch

class ApiDogViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ApiDogRepository
    val allDataBreed: LiveData<List<BreedDog>>
    val selectedBreedDog: MutableLiveData<BreedDog> = MutableLiveData()
    val selectedListDog: MutableLiveData<ListDog> = MutableLiveData()

    init {
        val apiDog = ApiDogBD.getDataBase(application).getBreedDogDao()
        repository = ApiDogRepository(apiDog)
        viewModelScope.launch {
            repository.getFetchApiDogCoroutines()
        }
        allDataBreed = repository.listAllBreed
    }

    fun returnImage(breed: String): LiveData<List<ListDog>>{
        return repository.getImageBreed(breed)
    }

    fun selectedImage(breed: String) = viewModelScope.launch{
        repository.getFetchApiDogCoroutinesString(breed)
    }

    fun selectedBreed(breedDog: BreedDog?){
        selectedBreedDog.value = breedDog
    }

    fun selectedList(listDog: ListDog?){
        selectedListDog.value = listDog
    }
}