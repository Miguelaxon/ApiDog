package com.example.apidog.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "list_dog")
data class ListDog(@PrimaryKey(autoGenerate = true) @NonNull var id: Int,
                   @SerializedName("message")
                   var list_dog: List<String>,
                   var status: String   )
