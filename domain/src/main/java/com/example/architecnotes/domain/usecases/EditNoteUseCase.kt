package com.example.architecnotes.domain.usecases

import com.example.architecnotes.domain.model.Note
import com.example.architecnotes.domain.repository.NoteRepository
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

   operator fun invoke(note: Note) = noteRepository.editNote(note)
}