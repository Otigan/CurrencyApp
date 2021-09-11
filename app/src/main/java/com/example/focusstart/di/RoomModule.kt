package com.example.focusstart.di

import android.app.Application
import androidx.room.Room
import com.example.focusstart.data.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {


    @Provides
    @Singleton
    fun provideDatabase(app: Application): CurrencyDatabase =
        Room.databaseBuilder(app, CurrencyDatabase::class.java, "currency_db").build()

}