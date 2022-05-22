package com.cendrawasih.mangan.data.source.remote

import com.cendrawasih.mangan.data.source.remote.api.ApiConfig
import com.cendrawasih.mangan.data.source.remote.response.Category
import com.cendrawasih.mangan.data.source.remote.response.Meal
import retrofit2.await

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply {
                    instance = this
                }
            }
    }

    suspend fun getCategories(callback: LoadCategoriesCallback) {
        ApiConfig.getApiService().getCategories().await().categories.let { listCategories ->
            callback.onCategoriesReceived(listCategories)
        }
    }

    suspend fun getBestSellerMeal(mealStr: String, callback: LoadBestMealCallback) {
        ApiConfig.getApiService().getBestSellerMeal(mealStr).await().meals.let { listMeals ->
            callback.onBestMealReceived(listMeals)
        }
    }

    suspend fun getDetailMeal(idMeal: String, callback: LoadDetailMealCallback) {
        ApiConfig.getApiService().getDetailMeal(idMeal).await().meals.let { detailMeal ->
            callback.onDetailMealReceived(detailMeal)
        }
    }

    interface LoadCategoriesCallback {
        fun onCategoriesReceived(categoriesResponse: List<Category>)
    }

    interface LoadBestMealCallback {
        fun onBestMealReceived(mealResponse: List<Meal>)
    }

    interface LoadDetailMealCallback {
        fun onDetailMealReceived(detailResponse: List<Meal>)
    }
}