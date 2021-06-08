package com.example.ricknmortyapp.ui.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ricknmortyapp.model.Resource
import com.example.ricknmortyapp.model.entity.location.Location
import com.example.ricknmortyapp.model.repository.remote.APIService
import com.example.ricknmortyapp.ui.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class LocationViewModelFactory(private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            return LocationViewModel(id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class LocationViewModel(val id: Int) : BaseViewModel() {
    var location: MutableLiveData<Resource<Location>> = MutableLiveData()

    @Inject
    lateinit var api: APIService

    init {
        getCharacter(id)
    }

    fun getCharacter(id: Int) = viewModelScope.launch {
        fetchCharacter(id)
    }


    private suspend fun fetchCharacter(id: Int) {
        location.postValue(Resource.Loading())
        try {
            val response = api.getLocationDetails(id)
            location.postValue(handleCharacterResponse(response))
        } catch (t: Throwable) {
            location.postValue(
                t.message?.let {
                    Resource.Error(
                        it
                    )
                }
            )
        }
    }

    private fun handleCharacterResponse(response: Response<Location>): Resource<Location> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}

