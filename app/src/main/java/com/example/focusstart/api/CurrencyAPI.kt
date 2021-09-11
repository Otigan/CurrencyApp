package com.example.focusstart.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyAPI {

    @GET("daily_json.js")
    suspend fun getCurrency(): Call<JsonObject>


    companion object {
        const val BASE_URL = "https://www.cbr-xml-daily.ru/"
    }

}