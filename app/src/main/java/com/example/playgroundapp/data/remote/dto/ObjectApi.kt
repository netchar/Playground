package com.example.playgroundapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ObjectApi (
	@SerializedName("page") val page : Int,
	@SerializedName("per_page") val per_page : Int,
	@SerializedName("total") val total : Int,
	@SerializedName("total_pages") val total_pages : Int,
	@SerializedName("data") val items : List<AuthorApi>
)