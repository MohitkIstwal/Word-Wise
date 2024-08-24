package com.example.dictionaryapp.domain.repository

import com.example.dictionaryapp.domain.model.WordItem
import com.example.dictionaryapp.util.Results
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {
    suspend fun getWordResult(
        word: String
    ): Flow<Results<WordItem>>
}
// word item cointains all info that we want to store
// the repository cointains all the data related to the word we input