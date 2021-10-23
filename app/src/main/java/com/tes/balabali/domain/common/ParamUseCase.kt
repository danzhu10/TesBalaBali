package com.tes.balabali.domain.common

abstract class ParamUseCase<in Param, T> {
    abstract suspend fun execute(param: Param): SimpleResult<T>
}