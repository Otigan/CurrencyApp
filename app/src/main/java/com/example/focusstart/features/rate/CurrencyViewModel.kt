package com.example.focusstart.features.rate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.focusstart.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    repo: Repository,
) : ViewModel() {

    var currencyRate = repo.getCurrencies().asLiveData()
}