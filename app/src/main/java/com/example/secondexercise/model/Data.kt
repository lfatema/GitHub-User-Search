package com.example.secondexercise.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<DetailedData>,
    @SerializedName("total_count")
    val totalCount: Int
)