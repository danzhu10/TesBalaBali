package com.tes.balabali.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemModel(
    var id: Int = 0,
    var name: String = "",
    var full_name: String = "",
    var owner: OwnerModel,
    var language: String? = "",
    var html_url: String="",
    var description: String?="",
    var created_at: String="",
    var updated_at: String="",
    var pushed_at: String="",
    var watchers: Int=0,
    var open_issues: Int=0,
    var forks: Int=0,
    var stargazers_count: Int=0
) : Parcelable