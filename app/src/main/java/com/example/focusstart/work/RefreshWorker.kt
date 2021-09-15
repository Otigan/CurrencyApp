package com.example.focusstart.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.lifecycle.asLiveData
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.focusstart.data.Repository
import com.example.focusstart.features.rate.CurrencyViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException


@HiltWorker
class RefreshWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: Repository,
) :
    Worker(appContext, workerParams) {

    companion object {
        const val WORK_NAME = "com.example.android.otigan.work.RefreshWork"
    }

    override fun doWork(): Result {

        try {
            repository.getCurrencies().asLiveData()
        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }
}