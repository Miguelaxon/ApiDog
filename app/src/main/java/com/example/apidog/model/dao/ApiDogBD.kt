package com.example.apidog.model.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apidog.model.BreedDog
import com.example.apidog.model.ListDog

@Database(entities = [BreedDog::class, ListDog::class], version = 1)
abstract class ApiDogBD : RoomDatabase() {
    abstract fun getBreedDogDao(): BreedDogDAO
    companion object {
        @Volatile
        private var INSTANCE: ApiDogBD? = null
        fun getDataBase(context: Context): ApiDogBD {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    ApiDogBD::class.java, "apiDogBD")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}