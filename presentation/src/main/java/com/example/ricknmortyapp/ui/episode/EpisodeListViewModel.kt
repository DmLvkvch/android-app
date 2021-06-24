package com.example.ricknmortyapp.ui.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.episode.Episode
import com.example.domain.interactors.IEpisodeInteractor
import com.example.domain.repository.Resource
import com.example.ricknmortyapp.ui.BaseViewModel
import com.example.ricknmortyapp.ui.adapter.episode.EpisodeByIdsPagingAdapterImpl
import com.example.ricknmortyapp.ui.adapter.episode.EpisodeByPagePagingAdapterImpl
import com.example.ricknmortyapp.ui.adapter.episode.EpisodePagingAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeListViewModel @Inject constructor(private val interactor: IEpisodeInteractor) :
    BaseViewModel<MutableList<Episode>>() {

    var items: MutableLiveData<Resource<MutableList<Episode>>> = MutableLiveData()

    var episodes: MutableList<Episode> = mutableListOf()

    var isLoading = false

    var adapter: EpisodePagingAdapter = EpisodeByPagePagingAdapterImpl(interactor)

    fun getNext() = viewModelScope.launch {
        isLoading = true
        fetchData()
        isLoading = false
    }

    fun getData(ids: String) = viewModelScope.launch {
        adapter = EpisodeByIdsPagingAdapterImpl(interactor, ids)
        fetchData(ids)
    }

    private suspend fun fetchData() {
        items.postValue(Resource.Loading())
        try {
            val response = adapter.getNextPagingData()
            response.let { items.value?.data?.addAll(it.results) }
            episodes.addAll(response.results)
            items.postValue(Resource.Success(episodes))
        } catch (t: Throwable) {
        }
    }

    private suspend fun fetchData(ids: String) {
        items.postValue(Resource.Loading())
        try {
            val response = interactor.getEpisodesByIds(ids)
            items.postValue(handleResponse(response))
        } catch (t: Throwable) {
            items.postValue(t.message?.let { Resource.Error(it) })
        }
    }

    fun isLastPage(): Boolean {
        return adapter.isLast()
    }
}