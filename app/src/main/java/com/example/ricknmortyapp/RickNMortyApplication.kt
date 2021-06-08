package com.example.ricknmortyapp

import android.app.Application
import dagger.Component


@Component
interface ApplicationComponent {  }

class RickNMortyApplication : Application() {
    val appComponent = DaggerApplicationComponent.create()
}