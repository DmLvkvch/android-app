package com.example.ricknmortyapp.ui.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ricknmortyapp.model.Resource
import com.example.ricknmortyapp.model.entity.location.LocationList
import com.example.ricknmortyapp.model.repository.remote.APIService
import com.example.ricknmortyapp.ui.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class LocationListViewModel : BaseViewModel() {
    var locations: MutableLiveData<Resource<LocationList>> = MutableLiveData()

    @Inject
    lateinit var api: APIService

    init {
        getLocations(1)
    }

    fun getLocations(page: Int) = viewModelScope.launch {
        fetchLocations(page)
    }


    private suspend fun fetchLocations(page: Int) {
        locations.postValue(Resource.Loading())
        try {
            val response = api.getLocationsByPage(page)
            locations.postValue(handleCharactersResponse(response))
        } catch (t: Throwable) {
            locations.postValue(
                t.message?.let {
                    Resource.Error(
                        it
                    )
                }
            )
        }
    }

    private fun handleCharactersResponse(response: Response<LocationList>): Resource<LocationList> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}