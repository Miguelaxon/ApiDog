package com.example.apidog.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BreedDogDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreedDog(breedDog: List<BreedDog>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllListDog(listDog: List<ListDog>)

    @Query("SELECT * FROM breed_dog")
    fun getAllBreedDog(): LiveData<List<BreedDog>>

    @Query("SELECT * FROM list_dog")
    fun getAllListDog(): LiveData<List<ListDog>>

    @Query("SELECT * FROM list_dog WHERE status = :mList")
    fun getListDog(mList: String): LiveData<List<ListDog>>

    @Update
    suspend fun updateImage(listDog: ListDog)

    @Query("SELECT * FROM list_dog WHERE favorites = 1")
    fun getAllImageFav(): LiveData<List<ListDog>>
}