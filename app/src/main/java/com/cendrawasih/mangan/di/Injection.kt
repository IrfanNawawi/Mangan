package com.cendrawasih.mangan.di

import android.content.Context
import com.cendrawasih.mangan.data.source.ManganRepository
import com.cendrawasih.mangan.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): ManganRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return ManganRepository.getInstance(remoteDataSource)
    }
}