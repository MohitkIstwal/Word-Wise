package com.example.dictionaryapp.Bookmark.domain.usecases

import com.example.dictionaryapp.Bookmark.domain.model.BookmarkClass
import com.example.dictionaryapp.Bookmark.domain.repository.BookmarkRepo

class AddBookmark(
    private val repository: BookmarkRepo
) {
    suspend operator fun invoke(note: BookmarkClass) {
        repository.deleteNote(note)
    }
}