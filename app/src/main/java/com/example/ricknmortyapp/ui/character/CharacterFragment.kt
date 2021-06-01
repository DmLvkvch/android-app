package com.example.ricknmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ricknmortyapp.BR
import com.example.ricknmortyapp.R
import com.example.ricknmortyapp.model.entity.character.Character


class CharacterFragment(var character: Character) : Fragment() {

    private lateinit var viewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater,
            R.layout.fragment_character,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        binding.setVariable(BR.character, character)
        return binding.root
    }

}