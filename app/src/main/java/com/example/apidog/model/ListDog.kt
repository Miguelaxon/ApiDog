package com.example.apidog.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "list_dog")
data class ListDog(@PrimaryKey(autoGenerate = true) @NonNull var id: Int = 0,
                   var list_dog: String,
                   var status: String)

