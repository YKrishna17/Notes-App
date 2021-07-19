package com.example.notes

import androidx.lifecycle.LiveData

class Repository(private val dao: Dao) {
    val allNotes: LiveData<List<Note>> = dao.getAllNotes()
    suspend fun insert(note: Note){
        dao.insert(note)
    }
    suspend fun delete(note: Note){
        dao.delete(note)
    }
}