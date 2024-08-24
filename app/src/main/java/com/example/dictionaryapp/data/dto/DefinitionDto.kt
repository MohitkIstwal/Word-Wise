package com.example.dictionaryapp.data.dto

import com.example.dictionaryapp.domain.model.Definition

data class DefinitionDto(
    val definition: String? = null,
    val example: String? = null
)

fun definitionDtoToDefinition(
    definitionDto: DefinitionDto?
) = Definition(
    definition = definitionDto?.definition ?: "",//?. represents the safe call operator
    example = definitionDto?.example ?: ""
)
