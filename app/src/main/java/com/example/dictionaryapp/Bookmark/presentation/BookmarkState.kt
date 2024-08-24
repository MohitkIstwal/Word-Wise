package com.example.dictionaryapp.Bookmark.presentation

import com.example.dictionaryapp.Bookmark.domain.model.BookmarkClass

data class BookmarkState (
    val notes: List<BookmarkClass> = emptyList()
)