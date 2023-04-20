package com.example.architecnotes.di

import android.content.Context
import androidx.room.Room
import com.example.architecnotes.data.local.NoteDao
import com.example.architecnotes.data.model.NoteDatabase
import com.example.architecnotes.data.repository.NoteRepositoryImpl
import com.example.architecnotes.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NoteAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context: Context,
        ) = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "note_db"
    ).allowMainThreadQueries().build()

    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()

    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }
}