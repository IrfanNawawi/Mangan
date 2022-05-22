package com.cendrawasih.mangan.data.source.remote.api

import com.cendrawasih.mangan.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    fun getCategories(): Call<CategoryResponse>

    @GET("search.php")
    fun getBestSellerMeal(@Query("f") mealStr: String): Call<MealResponse>

    @GET("lookup.php")
    fun getDetailMeal(@Query("i") idMeal: String): Call<MealResponse>
}