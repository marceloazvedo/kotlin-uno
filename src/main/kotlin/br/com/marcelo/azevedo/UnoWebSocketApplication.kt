package br.com.marcelo.azevedo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UnoWebSocketApplication

fun main(vararg args: String) {
    runApplication<UnoWebSocketApplication>(*args)
}
