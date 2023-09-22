package com.example.image_search

import com.google.gson.GsonBuilder
import com.jblee.imagesearch.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetWorkClient {

    val apiService: Network_Interface
        get() = instance.create(Network_Interface::class.java)


    private val instance: Retrofit
        private get() {
            val gson = GsonBuilder().setLenient().create()


            return Retrofit.Builder()
                .baseUrl(Constants.Search_URL)  // 기본 URL 설정
                .addConverterFactory(GsonConverterFactory.create(gson))  // JSON 파싱을 위한 컨버터 추가
                .build()
        }

}
