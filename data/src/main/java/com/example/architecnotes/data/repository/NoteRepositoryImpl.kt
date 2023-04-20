package com.example.architecnotes.data.repository

import com.example.architecnotes.data.base.BaseRepository
import com.example.architecnotes.data.local.NoteDao
import com.example.architecnotes.data.mappers.toEntity
import com.example.architecnotes.data.mappers.toNote
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
): com.example.architecnotes.domain.repository.NoteRepository, BaseRepository() {
    override fun createNote(note: com.example.architecnotes.domain.model.Note) = doRequest {
        noteDao.createNote(note.toEntity())
    }

    override fun getAllNotes() = doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }

    override fun editNote(note: com.example.architecnotes.domain.model.Note) = doRequest{
        noteDao.editNote(note.toEntity())
    }

    override fun delete(note: com.example.architecnotes.domain.model.Note) = doRequest {
        noteDao.deleteNote(note.toEntity())
    }

}