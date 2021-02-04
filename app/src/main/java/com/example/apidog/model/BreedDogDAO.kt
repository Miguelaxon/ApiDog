package com.example.apidog.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreedDogDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreedDog(breedDog: BreedDog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListDog(listDog: ListDog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreedDog(breedDog: BreedDog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllListDog(listDog: ListDog)

    @Query("SELECT * FROM breed_dog")
    fun getAllBreedDog(): LiveData<List<BreedDog>>

    @Query("SELECT * FROM list_dog")
    fun getAllListDog(): LiveData<List<ListDog>>

    @Query("SELECT * FROM breed_dog WHERE id = :mId")
    fun getBreedDog(mId: Int): LiveData<BreedDog>

    @Query("SELECT * FROM list_dog WHERE id = :mId")
    fun getListDog(mId: Int): LiveData<ListDog>
}