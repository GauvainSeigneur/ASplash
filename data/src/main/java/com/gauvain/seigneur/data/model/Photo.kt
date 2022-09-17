package com.gauvain.seigneur.data.model

data class Photo(
    val id: String,
    val width : Int,
    val height: Int,
    val color: String?,
    var description: String?,
    var liked_by_user:Boolean
)