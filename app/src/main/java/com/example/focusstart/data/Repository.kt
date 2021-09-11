package com.example.focusstart.data

import androidx.room.withTransaction
import com.example.focusstart.api.CurrencyAPI
import com.example.focusstart.util.networkBoundResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: CurrencyAPI,
    private val db: CurrencyDatabase
) {

    private val currencyDao = db.currencyDao()

    fun getCurrencies() = networkBoundResource(
        query = {
            currencyDao.getAllCurrencies()
        },
        fetch = {
            api.getCurrency()
        },
        saveFetchResult = {
            db.withTransaction {
                currencyDao.deleteAllCurrencies()
                val valute = it.Valute
                currencyDao.insertCurrencies(valute.getCurrencies())
            }
        },
        shouldFetch = {
            it == null
        }
    )
}