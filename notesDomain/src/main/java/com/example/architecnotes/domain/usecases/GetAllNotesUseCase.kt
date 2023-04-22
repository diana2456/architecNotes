package com.example.architecnotes.domain.usecases


import com.example.architecnotes.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    operator fun invoke() = noteRepository.getAllNotes()
}