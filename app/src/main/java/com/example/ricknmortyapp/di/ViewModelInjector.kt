package com.example.ricknmortyapp.di

import com.example.ricknmortyapp.ui.character.CharacterListViewModel
import com.example.ricknmortyapp.ui.character.CharacterViewModel
import com.example.ricknmortyapp.ui.location.LocationListViewModel
import com.example.ricknmortyapp.ui.location.LocationViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(viewModel: CharacterListViewModel)

    fun inject(characterViewModel: CharacterViewModel)

    fun inject(locationViewModel: LocationViewModel)

    fun inject(locationListViewModel: LocationListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}