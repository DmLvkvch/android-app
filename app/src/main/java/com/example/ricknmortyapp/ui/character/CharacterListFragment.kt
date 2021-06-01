package com.example.ricknmortyapp.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.ricknmortyapp.BR
import com.example.ricknmortyapp.R
import com.example.ricknmortyapp.model.entity.character.Character
import com.example.ricknmortyapp.ui.adapter.RecyclerBindingAdapter

class CharacterListFragment : Fragment() {

    private lateinit var characterListViewModel: CharacterListViewModel
    private lateinit var characterRecycleViewList: RecyclerView
    private var adapter: RecyclerBindingAdapter<Character> = RecyclerBindingAdapter(BR.character_item)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        characterListViewModel =
            ViewModelProvider(this).get(CharacterListViewModel::class.java)
        adapter.onClick = { item, _ ->
            apply {
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.nav_host_fragment, CharacterFragment(item))
                    addToBackStack(CharacterListFragment::class.java.canonicalName)
                    commit()
                }
            }
        }
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterRecycleViewList = view.findViewById(R.id.character_list_recycler)
        characterRecycleViewList.adapter = adapter
        characterListViewModel.characters.observe(viewLifecycleOwner, { item ->
            adapter.items = item.results
        })
    }
}