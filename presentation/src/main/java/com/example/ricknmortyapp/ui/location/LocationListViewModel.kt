package com.example.ricknmortyapp.ui.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.location.Location
import com.example.domain.interactors.ILocationInteractor
import com.example.domain.repository.Resource
import com.example.ricknmortyapp.ui.BaseViewModel
import com.example.ricknmortyapp.ui.adapter.location.LocationPagePagingAdapterImpl
import com.example.ricknmortyapp.ui.adapter.location.LocationPagingAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationListViewModel @Inject constructor(private val interactor: ILocationInteractor) :
    BaseViewModel<MutableList<Location>>() {

    var items: MutableLiveData<Resource<MutableList<Location>>> = MutableLiveData()

    var locations: MutableList<Location> = mutableListOf()

    var adapter: LocationPagingAdapter = LocationPagePagingAdapterImpl(interactor)

    var isLoading = false

    fun getNext() {
        getData()
    }

    fun getData() = viewModelScope.launch {
        fetchData()
    }

    private suspend fun fetchData() {
        items.postValue(Resource.Loading())
        try {
            val response = adapter.getNextPagingData()
            response.let { items.value?.data?.addAll(it.results) }
            locations.addAll(response.results)
            items.postValue(Resource.Success(locations))
        } catch (t: Throwable) {
        }
    }

    fun isLastPage(): Boolean {
        return adapter.isLast()
    }
}