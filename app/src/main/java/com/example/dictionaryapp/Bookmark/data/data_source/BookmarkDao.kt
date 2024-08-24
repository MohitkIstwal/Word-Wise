package com.example.dictionaryapp.Bookmark.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.dictionaryapp.Bookmark.domain.model.BookmarkClass
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmarkclass")
    fun getNotes(): Flow<List<BookmarkClass>>
    // this function returns a flow type data thus handles the asychronous opeartion internally doesnt require
    // the suspend function

    @Delete
    suspend fun deleteNote(note: BookmarkClass)
}