package com.example.notesapp.di

import com.example.notesapp.data.repository.Repository
import com.example.notesapp.data.repository.RepositoryImpl
import com.example.notesapp.data.database.dao.NoteDao
import com.example.notesapp.utils.DateProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
    @Singleton
    @Provides
    fun provideRepository(
        noteDao: NoteDao,
        ioDispatcher: CoroutineDispatcher,
        dateProvider: DateProvider
    ): Repository = RepositoryImpl(noteDao, ioDispatcher)

    @Singleton
    @Provides
    fun provideDateProvider(): DateProvider = DateProvider()
}