package com.example.architecnotes.data.mappers

import com.example.architecnotes.data.model.NoteEntity
import com.example.architecnotes.domain.model.Note

fun Note.toEntity() = NoteEntity(
    id, title, descriptions
)

fun NoteEntity.toNote() = Note(
    id, title, description
)