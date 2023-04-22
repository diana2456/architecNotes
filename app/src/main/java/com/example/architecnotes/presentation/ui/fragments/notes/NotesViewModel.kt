package com.example.architecnotes.presentation.ui.fragments.notes


import com.example.architecnotes.domain.model.Note
import com.example.architecnotes.domain.usecases.DeleteUseCase
import com.example.architecnotes.domain.usecases.GetAllNotesUseCase
import com.example.architecnotes.presentation.ui.base.BaseViewModel
import com.example.architecnotes.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteUseCase: DeleteUseCase,
) : BaseViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    private val _deleteNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNotesState = _deleteNoteState.asStateFlow()

    fun deleteNote(note: Note) {
        deleteUseCase(note).collectData(_deleteNoteState)
    }

    fun getAllNotes() {
        getAllNotesUseCase().collectData(_getAllNotesState)
    }
}