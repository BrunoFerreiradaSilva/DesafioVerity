package com.example.desafioverity.data.response

import com.google.gson.annotations.SerializedName

data class SearchDTO(
    @SerializedName("items")
    val items: List<UserDTO>
)