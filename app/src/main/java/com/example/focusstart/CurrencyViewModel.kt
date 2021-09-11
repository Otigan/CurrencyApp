package com.example.focusstart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.focusstart.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(repo: Repository) : ViewModel() {

    val currencyRate = repo.getCurrencies().asLiveData()
}