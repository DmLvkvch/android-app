package com.example.ricknmortyapp.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ricknmortyapp.BR
import com.example.ricknmortyapp.R
import com.example.ricknmortyapp.model.entity.location.Location
import com.example.ricknmortyapp.ui.adapter.PaginationScrollListener
import com.example.ricknmortyapp.ui.adapter.RecyclerBindingAdapter
import com.example.ricknmortyapp.ui.character.CharacterFragment
import com.example.ricknmortyapp.ui.character.CharacterListFragment

class LocationListFragment : Fragment() {

    lateinit var viewModel: LocationListViewModel
    lateinit var recyclerView: RecyclerView
    private var adapter: RecyclerBindingAdapter<Location> =
        RecyclerBindingAdapter(R.layout.item_location, BR.location_item)
    var currentPage = 1
    var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(LocationListViewModel::class.java)
        adapter.onClick = { item, _ ->
            apply {
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.nav_host_fragment, CharacterFragment(item.id))
                    addToBackStack(CharacterListFragment::class.java.canonicalName)
                    commit()
                }
            }
        }
        return inflater.inflate(R.layout.fragment_location_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.location_list_recycler)
        recyclerView.adapter = adapter
        viewModel.locations.observe(viewLifecycleOwner, { item ->
            val items = adapter.items
            item.data?.results?.let { items.addAll(it) }
            adapter.items = items
        })

        adapter.onLoadingData = {
            view.findViewById<ProgressBar>(R.id.location_list_pb).visibility = View.VISIBLE
        }

        adapter.onLoadingDataEnd = {
            view.findViewById<ProgressBar>(R.id.location_list_pb).visibility = View.INVISIBLE
        }

        recyclerView.addOnScrollListener(object :
            PaginationScrollListener(recyclerView.layoutManager as GridLayoutManager) {

            override fun isLastPage(): Boolean {
                return false
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                loadNextPage()
                isLoading = false
            }
        })
    }

    private fun loadNextPage() {
        currentPage++
        viewModel.getLocations(page = currentPage)
    }

}