package com.example.apidog.model.remote

import com.google.gson.annotations.SerializedName

data class BreedListDog(@SerializedName("message") val message: List<String>,
                        @SerializedName("status") val status: String)

data class ImageDog(@SerializedName("message") val message: List<String>,
                    @SerializedName("status") val status: String)