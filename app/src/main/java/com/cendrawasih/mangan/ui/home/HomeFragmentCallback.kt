package com.cendrawasih.mangan.ui.home

import com.cendrawasih.mangan.data.source.remote.response.Category
import com.cendrawasih.mangan.data.source.remote.response.Meal

interface HomeFragmentCallback {
    fun onDetailCategoryClick(category: Category)
    fun onDetailMealClick(detailMeal: Meal)
}