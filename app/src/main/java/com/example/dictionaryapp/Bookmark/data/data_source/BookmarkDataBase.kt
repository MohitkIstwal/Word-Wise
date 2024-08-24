package com.example.dictionaryapp.Bookmark.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dictionaryapp.Bookmark.domain.model.BookmarkClass

@Database(
    entities = [BookmarkClass::class],
    version = 1,
    exportSchema = false
)
abstract class BookmarkDatabase: RoomDatabase() {

    abstract val bookmarkDao: BookmarkDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}