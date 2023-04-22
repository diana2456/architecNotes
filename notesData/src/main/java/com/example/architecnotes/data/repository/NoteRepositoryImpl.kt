package com.example.architecnotes.data.repository

import com.example.architecnotes.data.base.BaseRepository
import com.example.architecnotes.data.local.NoteDao
import com.example.architecnotes.data.mappers.toEntity
import com.example.architecnotes.data.mappers.toNote
import com.example.architecnotes.domain.model.Note
import com.example.architecnotes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
): NoteRepository, BaseRepository() {
    override fun createNote(note: Note) = doRequest {
        noteDao.createNote(note.toEntity())
    }

    override fun getAllNotes() = doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }

    override fun editNote(note: Note) = doRequest{
        noteDao.editNote(note.toEntity())
    }

    override fun delete(note: Note) = doRequest {
        noteDao.deleteNote(note.toEntity())
    }

}