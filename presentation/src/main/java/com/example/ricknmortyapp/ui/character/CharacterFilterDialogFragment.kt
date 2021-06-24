package com.example.ricknmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.ricknmortyapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CharacterFilterDialogFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: CharacterFilterDialogViewModel

    private lateinit var name: EditText

    private lateinit var gender: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_filter_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = view.findViewById(R.id.character_filter_name)
        gender = view.findViewById(R.id.character_filter_gender)
        view.findViewById<Button>(R.id.apply_filter_button).setOnClickListener {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterFilterDialogViewModel::class.java)
    }
}