package com.github.freenote.di

import androidx.room.Room
import com.github.freenote.data.NoteDb
import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.data.repository.impl.RepositoryImpl
import com.github.freenote.data.repository.Repository
import com.github.freenote.data.repository.impl.FakeNoteRepo
import com.github.freenote.data.repository.impl.NoteImpl
import com.github.freenote.ui.noteslist.NotesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DATABASE = "note.db"

val viewModelModule = module {

    single<Repository> { RepositoryImpl() }
    single { Room.databaseBuilder(get(), NoteDb::class.java, DATABASE).build() }
    single { get<NoteDb>().noteDao() }
    single<NoteRepo> { NoteImpl(get()) }
    //viewModel { NotesListViewModel(get()) }
    viewModel { NotesListViewModel(FakeNoteRepo()) }
}