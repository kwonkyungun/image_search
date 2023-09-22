package com.example.image_search

import com.google.gson.annotations.SerializedName

data class ItemModel (

    val documents: ArrayList<Documents>,
    val meta: Meta

) {
    data class Meta(
        val isEnd: Boolean,
        val pageableCount: Int,
        val totalCount: Int
    )

    data class Documents(
        val collection: String,
        val thumbnailUrl: String,
        val imageUrl: String,
        val width: Int,
        val height: Int,
        val displaySitename: String,
        val docUrl: String,
        val datetime: String
    )

}
