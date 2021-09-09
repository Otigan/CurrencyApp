package com.example.focusstart

import com.example.focusstart.api.CurrencyAPI
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(private val api: CurrencyAPI) {

    suspend fun getRate() = api.getCurrency()

}