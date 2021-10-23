package com.tes.balabali.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OwnerModel(
    var login:String,
    var avatar_url: String= "",
    var url: String = "",
    var html_url: String = "",
) : Parcelable