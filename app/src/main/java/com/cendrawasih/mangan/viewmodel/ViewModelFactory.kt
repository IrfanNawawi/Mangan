package com.cendrawasih.mangan.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cendrawasih.mangan.data.source.ManganRepository
import com.cendrawasih.mangan.di.Injection
import com.cendrawasih.mangan.ui.home.HomeViewModel
import com.cendrawasih.mangan.ui.detail.DetailMealViewModel

class ViewModelFactory private constructor(private val mManganRepository: ManganRepository) : ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                instance = this
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(mManganRepository) as T
            }
            modelClass.isAssignableFrom(DetailMealViewModel::class.java) -> {
                return DetailMealViewModel(mManganRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}