package com.example.ricknmortyapp.ui

import androidx.lifecycle.ViewModel
import com.example.ricknmortyapp.di.DaggerViewModelInjector
import com.example.ricknmortyapp.di.NetworkModule
import com.example.ricknmortyapp.di.ViewModelInjector
import com.example.ricknmortyapp.ui.character.CharacterListViewModel
import com.example.ricknmortyapp.ui.character.CharacterViewModel
import com.example.ricknmortyapp.ui.location.LocationListViewModel
import com.example.ricknmortyapp.ui.location.LocationViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is CharacterListViewModel -> injector.inject(this)
            is CharacterViewModel -> injector.inject(this)
            is LocationViewModel -> injector.inject(this)
            is LocationListViewModel -> injector.inject(this)
        }
    }
}