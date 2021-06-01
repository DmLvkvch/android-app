package com.example.ricknmortyapp.model.utils.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIServiceRetrofitFactory {

    companion object {
        val retrofit: APIService by lazy {
            create()
        }

        private fun create(): APIService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
            return retrofit.create(APIService::class.java)
        }
    }
}