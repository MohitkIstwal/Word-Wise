package com.example.dictionaryapp.Bookmark.domain.repository

import com.example.dictionaryapp.Bookmark.domain.model.BookmarkClass
import kotlinx.coroutines.flow.Flow

interface BookmarkRepo {
    fun getNotes(): Flow<List<BookmarkClass>>

    suspend fun deleteNote(note: BookmarkClass)
}