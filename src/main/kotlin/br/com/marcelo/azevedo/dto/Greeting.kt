package br.com.marcelo.azevedo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Greeting(
    @JsonProperty
    val content: String,
)
