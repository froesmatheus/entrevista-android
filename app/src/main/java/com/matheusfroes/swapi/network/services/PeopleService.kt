package com.matheusfroes.swapi.network.services

import com.matheusfroes.swapi.network.data.GetPeopleResponse
import com.matheusfroes.swapi.network.data.PlanetResponse
import com.matheusfroes.swapi.network.data.SpecieResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleService {

    @GET("people")
    fun getPeople(
            @Query("page") page: Int = 1,
            @Query("search") query: String?
    ): Deferred<GetPeopleResponse>

    @GET("people")
    fun searchPeople(
            @Query("search") query: String,
            @Query("page") page: Int = 1
    ): Deferred<GetPeopleResponse>

    @GET("species/{id}")
    fun getSpecie(
            @Path("id") specieId: Int
    ): Deferred<SpecieResponse>

    @GET("planets/{id}")
    fun getPlanet(
            @Path("id") planetId: Int
    ): Deferred<PlanetResponse>
}