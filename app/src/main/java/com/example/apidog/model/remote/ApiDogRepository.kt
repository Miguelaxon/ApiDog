package com.example.apidog.model.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apidog.model.ApiDogClient
import com.example.apidog.model.BreedDog
import com.example.apidog.model.BreedDogDAO
import com.example.apidog.model.ListDog

class ApiDogRepository (private val breedDogDAO: BreedDogDAO){
    private val service = ApiDogClient.getApiDogClient()
    val liveDataBreedDog = MutableLiveData<List<BreedDog>>()

    val listAllBreed: LiveData<List<BreedDog>> = breedDogDAO.getAllBreedDog()

    fun converterBreed(listadoString: List<String>): List<BreedDog>{
        var listaBreedDog: MutableList<BreedDog> = mutableListOf()
        listadoString.map {
            listaBreedDog.add(BreedDog(breed_dog = it, status = ""))
        }
        return listaBreedDog
    }

    fun converterImage(listadoString: List<String>, breed: String): List<ListDog>{
        var listaListDog: MutableList<ListDog> = mutableListOf()
        listadoString.map {
            listaListDog.add(ListDog(list_dog = it, status = breed))
        }
        return listaListDog
    }

    suspend fun getFetchApiDogCoroutines(){
        try {
            val response = ApiDogClient.getApiDogClient().getFetchApiDog()
            when (response.isSuccessful){
                true -> response.body()?.let {
                    breedDogDAO.insertAllBreedDog(converterBreed(it.message))
                    //Log.d("REPO", "${it.message}")
                }
                false -> Log.d("ERROR", "${response.code()}: ${response.errorBody()}")
            }
        } catch (t: Throwable) {
            Log.d("Error Coroutina", t.message.toString())
        }
    }

    suspend fun getFetchApiDogCoroutinesString(breed: String) {
        try {
            val response = ApiDogClient.getApiDogClient().getFetchImageDog(breed)
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    breedDogDAO.insertAllListDog(converterImage(it.message, breed))
                }
                false -> Log.d("ERROR", "${response.code()}: ${response.errorBody()}")
            }
        } catch (t: Throwable) {
            Log.d("Error Coroutina", t.message.toString())
        }
    }

    fun getImageBreed(breed: String): LiveData<List<ListDog>>{
        return breedDogDAO.getListDog(breed)
    }
}