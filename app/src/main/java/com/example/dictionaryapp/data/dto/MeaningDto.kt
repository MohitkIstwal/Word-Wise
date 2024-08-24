package com.example.dictionaryapp.data.dto

import com.example.dictionaryapp.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>? = null,
    val partOfSpeech: String? = null
)

fun MeaningDto.toMeaning() = Meaning(
    definition = definitionDtoToDefinition(definitions?.get(0)),
    partOfSpeech = partOfSpeech ?: ""
)