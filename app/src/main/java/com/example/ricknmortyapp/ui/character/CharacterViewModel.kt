package com.example.ricknmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ricknmortyapp.model.entity.character.Character

class CharacterViewModel : ViewModel() {
    var character: MutableLiveData<Character> = MutableLiveData()
}