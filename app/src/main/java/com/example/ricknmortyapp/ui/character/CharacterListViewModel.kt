package com.example.ricknmortyapp.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ricknmortyapp.model.entity.character.CharacterList
import com.example.ricknmortyapp.model.utils.api.APIServiceRetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListViewModel : ViewModel() {
    var characters: MutableLiveData<CharacterList> = MutableLiveData()
    private val api = APIServiceRetrofitFactory.retrofit
    init {

        api.getAllUnits()
            .enqueue(object : Callback<CharacterList> {
                override fun onResponse(
                    call: Call<CharacterList>,
                    response: Response<CharacterList>
                ) {
                    characters.value = response.body()
                }

                override fun onFailure(call: Call<CharacterList>, t: Throwable) {
                    println(t.stackTrace)
                }
            })
    }
}