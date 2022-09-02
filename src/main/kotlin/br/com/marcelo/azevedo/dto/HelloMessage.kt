package br.com.marcelo.azevedo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class HelloMessage(
    @JsonProperty
    val name: String,
)
