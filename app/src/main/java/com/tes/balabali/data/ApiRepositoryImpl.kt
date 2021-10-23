package com.tes.balabali.data

import com.tes.balabali.data.common.mapToResult
import com.tes.balabali.data.service.ApiService
import com.tes.balabali.domain.common.Result
import com.tes.balabali.domain.common.SimpleError
import com.tes.balabali.domain.common.SimpleResult
import com.tes.balabali.domain.model.DataModel

class ApiRepositoryImpl(
    private val apiService: ApiService
) {

    suspend fun searchRepo(
        query: String, sort: String
    ): SimpleResult<DataModel> {
        return try {
            apiService.searchRepo(query, sort).mapToResult()
        } catch (e: Exception) {
            Result.Failure(SimpleError(e.localizedMessage))
        }
    }
}