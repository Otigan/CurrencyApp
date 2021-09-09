package com.example.focusstart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.focusstart.model.Currency
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    val currencyRate = MutableLiveData<List<Currency>>()
    var job: Job? = null

    fun getRate() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repo.getRate()
            val gson = Gson()
            withContext(Dispatchers.Default) {
                response.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {

                        Log.d("CURRENCY_VIEW_MODEL", "onResponse: WORKING")
                        val body = response.body()
                        val valute = body?.getAsJsonObject("Valute")
                        val list = ArrayList<Currency>()

                        for (item in valute?.keySet()!!) {
                            val it = gson.fromJson(valute[item].asJsonObject, Currency::class.java)
                            list.add(it)
                        }
                        currencyRate.postValue(list)
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.d("CURRENCY_VIEW_MODEL", "onFailure: $t")
                    }
                })
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}