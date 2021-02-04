package com.example.apidog.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "list_dog",
    foreignKeys = [ForeignKey(
        entity = BreedDog::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("idBreedDog"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ListDog(@PrimaryKey(autoGenerate = true) @NonNull var id: Int = 0,
                   @SerializedName("message")
                   var list_dog: String,
                   var status: String,
                   var idBreedDog: Int)

