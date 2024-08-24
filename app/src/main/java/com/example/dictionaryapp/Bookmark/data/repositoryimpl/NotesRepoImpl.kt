package com.example.dictionaryapp.Bookmark.data.repositoryimpl

import com.example.dictionaryapp.Bookmark.data.data_source.BookmarkDao
import com.example.dictionaryapp.Bookmark.domain.model.BookmarkClass
import com.example.dictionaryapp.Bookmark.domain.repository.BookmarkRepo
import kotlinx.coroutines.flow.Flow

class BookmarkRepositoryImpl(
    private val dao: BookmarkDao
) : BookmarkRepo {
    override fun getNotes(): Flow<List<BookmarkClass>> {
        return dao.getNotes()
    }
    override suspend fun deleteNote(note:BookmarkClass) {
        dao.deleteNote(note)
    }
}