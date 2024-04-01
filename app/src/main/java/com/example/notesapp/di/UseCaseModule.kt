package com.example.notesapp.di

import com.example.notesapp.domain.repository.Repository
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.domain.usecase.GetNotesUseCase
import com.example.notesapp.domain.usecase.SaveNoteUseCase
import com.example.notesapp.domain.usecase.SearchNotesUseCase
import com.example.notesapp.domain.usecase.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetNotesUseCase(repository: Repository): GetNotesUseCase {
        return GetNotesUseCase(repository)
    }

    @Provides
    fun provideSearchNotesUseCase(repository: Repository): SearchNotesUseCase {
        return SearchNotesUseCase(repository)
    }

    @Provides
    fun provideSaveNoteUseCase(repository: Repository): SaveNoteUseCase {
        return SaveNoteUseCase(repository)
    }

    @Provides
    fun provideUpdateNoteUseCase(repository: Repository): UpdateNoteUseCase {
        return UpdateNoteUseCase(repository)
    }

    @Provides
    fun provideDeleteNoteUseCase(repository: Repository): DeleteNoteUseCase {
        return DeleteNoteUseCase(repository)
    }
}
