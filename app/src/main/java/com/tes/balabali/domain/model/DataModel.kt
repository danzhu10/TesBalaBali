package com.tes.balabali.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModel(
    var total_count: Int = 0,
    var items : ArrayList<ItemModel>
) : Parcelable