package com.example.dictionaryapp.data.dto

import com.example.dictionaryapp.domain.model.WordItem

data class WordItemDto(
    val meanings: List<MeaningDto>? = null,
    val phonetic: String? = null,
    val word: String? = null
)

fun WordItemDto.toWordItem() = WordItem (
    word = word ?: "",
    meanings = meanings?.map {
        it.toMeaning()
    } ?: emptyList(),
    phonetic = phonetic ?: ""
)
