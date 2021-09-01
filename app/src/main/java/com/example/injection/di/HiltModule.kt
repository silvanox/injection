package com.example.injection.di

import com.example.injection.service.GithubServices
import com.example.injection.service.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    fun providesGithubServices(): GithubServices = RetrofitService.getGithubServices()
}

