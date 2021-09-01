package com.example.injection.repository

import com.example.injection.model.User
import com.example.injection.service.GithubServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GithubUser @Inject constructor (private val githubServices: GithubServices) {

    fun fetchUsers(onComplete: (List<User>?, String?) -> Unit) {

        githubServices.fetchUsers().enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.body() != null) {
                    onComplete(response.body(), null)
                }
                else {
                    onComplete(null, "Sorry we could not find any user!")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                onComplete(null, t.message)
            }

        })

    }

}

