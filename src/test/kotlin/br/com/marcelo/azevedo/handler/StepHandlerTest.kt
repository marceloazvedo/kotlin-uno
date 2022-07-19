package br.com.marcelo.azevedo.handler

import br.com.marcelo.azevedo.mediator.Mediator
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach

abstract class StepHandlerTest {

    protected val mediator: Mediator = mockk()

    @BeforeEach
    fun setupTest() {
        every { mediator.notify(any(), any()) } returns Unit
        every { mediator.notify(any(), any(), any()) } returns Unit
    }

}