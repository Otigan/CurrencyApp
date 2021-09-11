package com.example.focusstart.api

import com.example.focusstart.model.ResponseApi
import retrofit2.http.GET

interface CurrencyAPI {

    @GET("daily_json.js")
    suspend fun getCurrency(): ResponseApi


    companion object {
        const val BASE_URL = "https://www.cbr-xml-daily.ru/"
    }

}