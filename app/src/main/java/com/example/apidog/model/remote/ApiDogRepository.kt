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
    val liveDataListDog = MutableLiveData<List<ListDog>>()
    val listAllBreed: LiveData<List<BreedDog>> = breedDogDAO.getAllBreedDog()
    val listAllList: LiveData<List<ListDog>> = breedDogDAO.getAllListDog()

    suspend fun insertAllBreed(breedDog: BreedDog){
        breedDogDAO.insertAllBreedDog(breedDog)
    }

    suspend fun insertAllList(listDog: ListDog){
        breedDogDAO.insertAllListDog(listDog)
    }

    suspend fun getFetchApiDogCoroutines(){
        try {
            val response = ApiDogClient.getApiDogClient().getFetchApiDog()
            when (response.isSuccessful){
                true -> response.body()?.let {
                    insertAllBreed(it)
                    val response2 = ApiDogClient.getApiDogClient().getFetchImageDog(it.breed_dog.toString())
                    when (response2.isSuccessful){
                        true -> response2.body()?.let {
                            insertAllList(it)
                        }
                        false -> Log.d("ERROR", "${response.code()}: ${response.errorBody()}")
                    }
                }
                false -> Log.d("ERROR", "${response.code()}: ${response.errorBody()}")
            }
        }
        catch (t: Throwable) {
            Log.d("Error Coroutina", t.message.toString())
        }
    }
}