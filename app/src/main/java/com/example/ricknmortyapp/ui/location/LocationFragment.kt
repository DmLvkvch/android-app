package com.example.ricknmortyapp.ui.location

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.ricknmortyapp.BR
import com.example.ricknmortyapp.R

class LocationFragment(private val locationId: Int) : Fragment() {

    private lateinit var viewModel: LocationViewModel
    private lateinit var viewModelFactory: LocationViewModelFactory
    private lateinit var binding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_location,
            container,
            false
        )
        viewModelFactory = LocationViewModelFactory(locationId)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(LocationViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.location.observe(viewLifecycleOwner, { item ->
            binding.setVariable(BR.location, item.data)
        })

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}