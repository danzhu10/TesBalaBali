package com.tes.balabali.domain.common

data class SimpleError(
    var errorMessage: String = ""
) : Error()