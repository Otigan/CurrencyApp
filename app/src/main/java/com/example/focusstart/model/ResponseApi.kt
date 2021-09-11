package com.example.focusstart.model

import com.example.focusstart.model.Valute

data class ResponseApi(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: Valute
)