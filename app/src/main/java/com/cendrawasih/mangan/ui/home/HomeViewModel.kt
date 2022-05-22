package com.cendrawasih.mangan.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cendrawasih.mangan.data.source.ManganRepository
import com.cendrawasih.mangan.data.source.remote.response.Category
import com.cendrawasih.mangan.data.source.remote.response.Meal

class HomeViewModel(private val homeRepository: ManganRepository) : ViewModel() {
    fun getCategory(): LiveData<List<Category>> = homeRepository.getAllCategories()
    fun getBestMeal(mealStr: String): LiveData<List<Meal>> = homeRepository.getBestMeal(mealStr)
}