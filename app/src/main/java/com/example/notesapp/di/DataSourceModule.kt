package com.example.notesapp.di

import com.example.notesapp.data.database.dao.NoteDao
import com.example.notesapp.data.datasource.LocalNoteDataSource
import com.example.notesapp.data.datasource.NoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideNoteDataSource(
        noteDao: NoteDao,
        ioDispatcher: CoroutineDispatcher
    ): NoteDataSource = LocalNoteDataSource(noteDao, ioDispatcher)
}
