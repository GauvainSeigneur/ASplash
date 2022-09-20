package com.gauvain.seigneur.data.model.photo

data class PhotoUrl(
    //Fallback in case of is null,
    var thumb: String,
    var small: String?,
    var regular: String?,
    var full: String? ,
    var raw: String?
)