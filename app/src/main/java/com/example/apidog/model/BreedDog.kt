package com.example.apidog.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "breed_dog")
data class BreedDog(@PrimaryKey
                    var breed_dog: String,
                    var status: String)