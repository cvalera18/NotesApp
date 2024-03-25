package com.example.notesapp.di

import com.example.notesapp.data.repository.Repository
import com.example.notesapp.data.repository.RepositoryImpl
import com.example.notesapp.model.NoteProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRepository(noteProvider: NoteProvider): Repository = RepositoryImpl(noteProvider)
}