package com.example.ricknmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ricknmortyapp.model.Resource
import com.example.ricknmortyapp.model.entity.character.CharacterList
import com.example.ricknmortyapp.model.repository.remote.APIService
import com.example.ricknmortyapp.ui.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


class CharacterListViewModel() : BaseViewModel() {
    var characters: MutableLiveData<Resource<CharacterList>> = MutableLiveData()

    @Inject
    lateinit var api: APIService

    var page: Int = 1

    var isLoading = false

    init {
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        isLoading = true
        fetchCharacters(page)
        isLoading = false
    }


    private suspend fun fetchCharacters(page: Int) {
        characters.postValue(Resource.Loading())
        try {
            val response = api.getCharactersByPage(page)
            characters.postValue(handleCharactersResponse(response))
        } catch (t: Throwable) {
            characters.postValue(
                t.message?.let {
                    Resource.Error(
                        it
                    )
                }
            )
        }
    }

    private fun handleCharactersResponse(response: Response<CharacterList>): Resource<CharacterList> {
        if (response.isSuccessful) {
            page++
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun isLastPage(): Boolean = page > characters.value?.data?.info?.pages ?: Int.MAX_VALUE
}