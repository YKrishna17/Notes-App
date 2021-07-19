package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    private val repo: Repository
    init {
        val dao=RoomDB.getDatabase(application).dao()
        repo=Repository(dao)
        allNotes= repo.allNotes
    }
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note)
    }
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }
}