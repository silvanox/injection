package com.example.injection.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar: String
)
