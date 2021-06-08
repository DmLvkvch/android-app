package com.example.ricknmortyapp.model.repository.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIServiceRetrofitFactory {

    companion object {
        private const val API_ENDPOINT = "https://rickandmortyapi.com/api/"
        val retrofit: APIService by lazy {
            create()
        }

        private fun create(): APIService {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
            return retrofit.create(APIService::class.java)
        }
    }
}