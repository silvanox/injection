package com.example.injection.service

import com.example.injection.model.User
import retrofit2.Call
import retrofit2.http.GET

interface GithubServices {
    @GET("/users")
    fun fetchUsers(): Call<List<User>>
}
