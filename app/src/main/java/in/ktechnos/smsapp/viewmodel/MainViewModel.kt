package `in`.ktechnos.smsapp.viewmodel

import `in`.ktechnos.smsapp.data.local.NoteDatabase
import `in`.ktechnos.smsapp.data.local.NoteEntity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch

class MainViewModel(private val noteDatabase: NoteDatabase) : ViewModel() {

    val notes = noteDatabase.dao.getAllNotes()

    fun insertNote(noteEntity: NoteEntity){
        viewModelScope.launch{
            noteDatabase.dao.insertNote(noteEntity)
        }
    }
}