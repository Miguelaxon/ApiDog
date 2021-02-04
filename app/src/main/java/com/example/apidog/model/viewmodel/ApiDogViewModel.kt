package com.example.apidog.model.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.apidog.model.BreedDog
import com.example.apidog.model.ListDog
import com.example.apidog.model.local.ApiDogBD
import com.example.apidog.model.remote.ApiDogRepository
import kotlinx.coroutines.launch

class ApiDogViewModel(appication: Application): AndroidViewModel(appication) {
    private val repository: ApiDogRepository
    val allDataBreed: LiveData<List<BreedDog>>
    val allDataList: LiveData<List<ListDog>>
    val selectedBreedDog: MutableLiveData<BreedDog> = MutableLiveData()
    val selectedListDog: MutableLiveData<ListDog> = MutableLiveData()

    init {
        val apiDog = ApiDogBD.getDataBase(appication).getBreedDogDao()
        repository = ApiDogRepository(apiDog)
        viewModelScope.launch {
            repository.getFetchApiDogCoroutines()
        }
        allDataBreed = repository.listAllBreed
        allDataList = repository.listAllList
    }

    fun selectedBreed(breedDog: BreedDog?){
        selectedBreedDog.value = breedDog
    }

    fun selectedList(listDog: ListDog?){
        selectedListDog.value = listDog
    }
}