package com.example.ricknmortyapp.di

import com.example.ricknmortyapp.ui.character.CharacterFragment
import com.example.ricknmortyapp.ui.character.CharacterListFragment
import com.example.ricknmortyapp.ui.location.LocationFragment
import com.example.ricknmortyapp.ui.location.LocationListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface FragmentInjector {

    fun inject(value: CharacterListFragment)

    fun inject(value: CharacterFragment)

    fun inject(value: LocationListFragment)

    fun inject(value: LocationFragment)

    @Component.Builder
    interface Builder {
        fun build(): FragmentInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}