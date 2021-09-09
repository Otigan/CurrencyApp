package com.example.focusstart.di

import com.example.focusstart.api.CurrencyAPI
import com.example.focusstart.api.CurrencyAPI.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): CurrencyAPI {
        return retrofit.create(CurrencyAPI::class.java)
    }
}