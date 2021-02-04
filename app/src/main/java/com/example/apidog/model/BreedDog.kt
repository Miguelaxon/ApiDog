package com.example.apidog.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "breed_dog")
data class BreedDog(@PrimaryKey(autoGenerate = true) @NonNull var id: Int,
                    @SerializedName("message")
                    var breed_dog: List<String>,
                    var status: String)
