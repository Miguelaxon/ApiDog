package com.example.apidog.model.dao

import com.example.apidog.model.remote.BreedListDog
import com.example.apidog.model.remote.ImageDog
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiDog {
    @GET("breeds/list")
    suspend fun getFetchApiDog(): Response<BreedListDog>

    @GET("breed/{breed}/images")
    suspend fun getFetchImageDog(@Path("breed") breed: String): Response<ImageDog>
}