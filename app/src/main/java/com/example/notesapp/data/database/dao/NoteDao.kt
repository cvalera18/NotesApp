package com.example.notesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.data.database.entities.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY date DESC")
    suspend fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM note_table WHERE LOWER(title) LIKE LOWER( :searchQuery) OR LOWER(body) LIKE LOWER( :searchQuery) ORDER BY date DESC")
    suspend fun searchNotes(searchQuery: String): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)
}