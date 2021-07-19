package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
    @Delete
    suspend fun delete(note: Note)
    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<Note>>

}