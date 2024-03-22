package com.example.notesapp.di

import com.example.notesapp.data.repository.Repository
import com.example.notesapp.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    fun provideRepository(): Repository = RepositoryImpl
}