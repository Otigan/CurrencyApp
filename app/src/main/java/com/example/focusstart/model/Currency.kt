package com.example.focusstart.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    val ID: String,
    val NumCode: Int,
    val CharCode: String,
    val Nominal: Int,
    val Name: String,
    val Value: Double,
    val Previous: Double
) : Parcelable