package com.example.notesapp.di

import com.example.notesapp.adapter.NotesAdapterFactory
import com.example.notesapp.adapter.NotesAdapterFactoryImpl
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