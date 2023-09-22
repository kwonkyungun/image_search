package com.example.image_search.Search

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Search_Model(
    var dateTime: String,
    var Title: String,
    var Url: String,
    var Like: Boolean = false
):Parcelable