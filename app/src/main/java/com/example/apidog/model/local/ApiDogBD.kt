package com.example.apidog.model.local

import androidx.room.Database
import com.example.apidog.model.BreedDog

@Database(entities = [BreedDog::class], version = 1)
abstract class ApiDogBD {
}