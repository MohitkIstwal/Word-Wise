package com.example.dictionaryapp.Bookmark.domain.model

import androidx.room.Entity

@Entity
data class BookmarkClass(
    val author: String,
    val quote: String
)
