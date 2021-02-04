package com.example.apidog.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiDog {
    @GET("breeds/list")
    suspend fun getFetchApiDog(): Response<BreedDog>

    @GET("breed/{breed}/images")
    suspend fun getFetchImageDog(@Path("breed") breed: String): Response<ListDog>
}