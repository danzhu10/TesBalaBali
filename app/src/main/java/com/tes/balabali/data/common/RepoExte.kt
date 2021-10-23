package com.tes.balabali.data.common

import com.tes.balabali.domain.common.Result
import com.tes.balabali.domain.common.SimpleError
import com.tes.balabali.domain.common.SimpleResult

fun <Model> SimpleResponseObject<Model>.mapToResult(): SimpleResult<Model> {
    return when {
        this.isSuccessful -> {
            val body = this.body()
            when {
                body != null -> {
                    Result.Success.Data<Model>(body()!!)
                }
                else -> {
                    Result.Failure(SimpleError("Success but unknown failure"))
                }
            }
        }
        else -> Result.Failure(SimpleError(this.errorBody()?.string().orEmpty()))
    }
}