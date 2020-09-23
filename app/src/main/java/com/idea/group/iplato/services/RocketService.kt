package com.idea.group.iplato.services


import com.idea.group.iplato.models.responce.RocketModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RocketService {
    @GET("rockets")
    fun getRockets(@Query("limit") limit: Int, @Query("offset") offset: Int):Call<List<RocketModel>>
}