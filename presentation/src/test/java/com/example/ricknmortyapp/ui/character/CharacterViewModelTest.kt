package com.example.ricknmortyapp.ui.character

import com.example.domain.entities.character.Character
import com.example.domain.interactors.ICharacterInteractor
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterViewModelTest {
    lateinit var viewModel: CharacterViewModel

    @Test
    suspend fun start() {
        val service = mockk<ICharacterInteractor>()
        every { runBlocking { service.getCharacterById(1) } } returns Character(1)

        viewModel = CharacterViewModel(service)
        viewModel.id = 1
        viewModel.fetch()
        assertEquals(viewModel.character, Character(1))
    }
}