package com.cendrawasih.mangan.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cendrawasih.mangan.data.source.ManganRepository
import com.cendrawasih.mangan.data.source.remote.response.Meal

class DetailMealViewModel(private val manganRepository: ManganRepository) : ViewModel() {
    fun getDetailMeal(idMeal: String): LiveData<List<Meal>> = manganRepository.getDetailMeal(idMeal)
}