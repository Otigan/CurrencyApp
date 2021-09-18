package com.example.focusstart.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.room.withTransaction
import com.example.focusstart.api.CurrencyAPI
import com.example.focusstart.util.networkBoundResource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: CurrencyAPI,
    private val db: CurrencyDatabase,
    @ApplicationContext private val context: Context
) {

    private val currencyDao = db.currencyDao()

    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    private val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

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
            isConnected
        }

    )
}