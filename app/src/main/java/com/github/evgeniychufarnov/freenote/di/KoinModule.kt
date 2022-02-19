package com.github.evgeniychufarnov.freenote.di

import androidx.room.Room
import com.github.evgeniychufarnov.freenote.data.NoteDb
import com.github.evgeniychufarnov.freenote.data.repository.NoteRepo
import com.github.evgeniychufarnov.freenote.data.repository.impl.RepositoryImpl
import com.github.evgeniychufarnov.freenote.data.repository.Repository
import com.github.evgeniychufarnov.freenote.data.repository.impl.NoteImpl
import com.github.evgeniychufarnov.freenote.ui.note.NoteViewModel
import com.github.evgeniychufarnov.freenote.ui.open.OpenNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NoteViewModel(get()) }
    viewModel { OpenNoteViewModel(get()) }
    single<Repository> { RepositoryImpl() }
    val dbPatchRepositories = "note.db"
    single { Room.databaseBuilder(get(), NoteDb::class.java, dbPatchRepositories).build() }
    single { get<NoteDb>().noteDao() }
    single<NoteRepo> { NoteImpl(get()) }
}