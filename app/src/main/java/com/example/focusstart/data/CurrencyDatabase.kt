package com.example.focusstart.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.focusstart.model.Currency

@Database(entities = [Currency::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao
}