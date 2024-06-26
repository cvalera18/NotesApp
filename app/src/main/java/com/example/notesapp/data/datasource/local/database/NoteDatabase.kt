package com.example.notesapp.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.data.datasource.local.database.dao.NoteDao
import com.example.notesapp.data.datasource.local.database.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 2)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}