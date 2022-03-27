package com.github.freenote.di

import androidx.room.Room
import com.github.freenote.data.NoteDb
import com.github.freenote.data.repository.NoteRepo
import com.github.freenote.data.repository.ThemesRepo
import com.github.freenote.data.repository.impl.NoteImpl
import com.github.freenote.data.repository.impl.ThemesRepoImpl
import com.github.freenote.ui.MainViewModel
import com.github.freenote.ui.note.NoteViewModel
import com.github.freenote.ui.noteslist.NotesListViewModel
import com.github.freenote.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DATABASE = "note.db"

val viewModelModule = module {

    single { Room.databaseBuilder(get(), NoteDb::class.java, DATABASE).build() }
    single { get<NoteDb>().noteDao() }
    single<NoteRepo> { NoteImpl(get()) }
    single<ThemesRepo> { ThemesRepoImpl(get()) }
    viewModel { NotesListViewModel(get(), get()) }
    viewModel { NoteViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { MainViewModel(get()) }
}