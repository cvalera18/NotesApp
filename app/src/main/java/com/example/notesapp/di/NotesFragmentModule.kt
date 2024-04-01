package com.example.notesapp.di

import com.example.notesapp.presentation.adapter.NotesAdapterFactory
import com.example.notesapp.presentation.adapter.NotesAdapterFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object NotesFragmentModule {

    @Provides
    fun provideNotesAdapterFactory(): NotesAdapterFactory = NotesAdapterFactoryImpl()
}