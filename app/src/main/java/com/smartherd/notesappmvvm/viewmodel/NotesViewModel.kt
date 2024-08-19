package com.smartherd.notesappmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.smartherd.notesappmvvm.database.NotesDatabase
import com.smartherd.notesappmvvm.model.Notes
import com.smartherd.notesappmvvm.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository : NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    fun getNotes() : LiveData<List<Notes>> = repository.getAllNotes()

    fun getLowNotes() : LiveData<List<Notes>> = repository.getLowNotes()

    fun getMediumNotes() : LiveData<List<Notes>> = repository.getMediumNotes()

    fun getHighNotes() : LiveData<List<Notes>> = repository.getHighNotes()

    fun deleteNotes(id : Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}