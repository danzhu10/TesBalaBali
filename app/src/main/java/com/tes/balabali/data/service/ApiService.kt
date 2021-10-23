package com.tes.balabali.data.service

import com.tes.balabali.data.common.SimpleResponseObject
import com.tes.balabali.domain.model.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("repositories")
    suspend fun searchRepo(
        @Query("q") query: String,
        @Query("sort") sort: String
    ): SimpleResponseObject<DataModel>

    /*
    * https://api.github.com/search/repositories?q=language:python&sort=stars
    * pake Header Accept : application/vnd.github.v3+json*/

}