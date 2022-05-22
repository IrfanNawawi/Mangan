package com.cendrawasih.mangan.data.source

import androidx.lifecycle.LiveData
import com.cendrawasih.mangan.data.source.remote.response.Category
import com.cendrawasih.mangan.data.source.remote.response.Meal

interface ManganDataSource {
    fun getAllCategories(): LiveData<List<Category>>
    fun getBestMeal(mealStr: String): LiveData<List<Meal>>
    fun getDetailMeal(idMeal: String): LiveData<List<Meal>>
}