package com.tes.balabali.data.mapper

interface Mapper<Response, Model> {

    fun mapFromResponse(response: Response): Model
}
