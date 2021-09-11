package com.example.focusstart.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.focusstart.model.Currency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): Flow<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currencies: List<Currency>)

    @Query("DELETE FROM currencies")
    suspend fun deleteAllCurrencies()
}