@file:Suppress("DEPRECATION")

package com.example.architecnotes.presentation.ui.fragments.add_edit_notes

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.architecnotes.R
import com.example.architecnotes.databinding.FragmentAddEditNotesBinding
import com.example.architecnotes.domain.model.Note
import com.example.architecnotes.presentation.ui.base.BaseFragment
import com.example.architecnotes.presentation.ui.fragments.notes.NotesFragment.Companion.EDIT
import com.example.architecnotes.presentation.utils.UIState
import com.example.architecnotes.presentation.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddEditNotesFragment : BaseFragment(R.layout.fragment_add_edit_notes) {

    private val viewModel: AddNoteViewModel by viewModels()
    private val binding by viewBinding(FragmentAddEditNotesBinding::bind)
    private var note: Note? = null
    private var noteIsNull = true


    override fun setupRequests() {
        addOrEdit()
    }

    override fun initSubscribers() {
        setupSubscribers()
        collectEditNote()
    }

    @SuppressLint("SetTextI18n")
    private fun getNote() {
        if (arguments?.getSerializable(EDIT) == null) {
            note = Note()
        } else {
            note = arguments?.getSerializable(EDIT) as Note
            binding.etTitle.setText(note!!.title)
            binding.etDesc.setText(note!!.descriptions)
            binding.btnSave.text = "Edit"
            noteIsNull = false
        }
    }

    private fun addOrEdit() {
        with(binding) {
            btnSave.setOnClickListener {
                if (binding.etTitle.text!!.isNotEmpty() && binding.etDesc.text!!.isNotEmpty()) {
                    note?.title = etTitle.text.toString()
                    note?.descriptions = etDesc.text.toString()
                    if (noteIsNull) {
                        viewModel.createNote(note!!)
                    } else {
                        viewModel.editNote(note!!)
                    }
                } else {
                    context?.showToast(
                        "Поля не должны быть пустыми"
                    )
                }
            }
        }
    }

    override fun setupSubscribers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addNoteState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {}
                        is UIState.Success -> {
                            findNavController().navigateUp()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun initialize() {
        getNote()
    }

    private fun collectEditNote() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.editNoteState.collect { state ->
                    when (state) {
                        is UIState.Loading -> {}
                        is UIState.Success -> {
                            findNavController().navigateUp()
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}