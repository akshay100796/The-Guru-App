package com.codexdroid.theguru.di.network.services

import com.codexdroid.theguru.di.room.tables.TableLearnings
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitPublicService {

    @GET("akshay100796/The-Guru-App-Notes/main/text/learnings.json")
    suspend fun requestLoadLearnings() : Response<List<TableLearnings>>



}