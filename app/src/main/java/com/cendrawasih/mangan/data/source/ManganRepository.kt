package com.cendrawasih.mangan.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cendrawasih.mangan.data.source.remote.RemoteDataSource
import com.cendrawasih.mangan.data.source.remote.response.Category
import com.cendrawasih.mangan.data.source.remote.response.Meal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ManganRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    ManganDataSource {

    companion object {
        @Volatile
        private var instance: ManganRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): ManganRepository =
            instance ?: synchronized(this) {
                instance ?: ManganRepository(remoteDataSource)
            }
    }

    override fun getAllCategories(): LiveData<List<Category>> {
        val categoryResults = MutableLiveData<List<Category>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getCategories(object : RemoteDataSource.LoadCategoriesCallback {
                override fun onCategoriesReceived(categoriesResponse: List<Category>) {
                    val categoryList = ArrayList<Category>()
                    for (response in categoriesResponse) {
                        val category = Category(
                            response.idCategory,
                            response.strCategory,
                            response.strCategoryDescription,
                            response.strCategoryThumb
                        )
                        categoryList.add(category)
                    }
                    categoryResults.postValue(categoryList)
                }

            })
        }
        return categoryResults
    }

    override fun getBestMeal(mealStr: String): LiveData<List<Meal>> {
        val mealResults = MutableLiveData<List<Meal>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getBestSellerMeal(
                mealStr,
                object : RemoteDataSource.LoadBestMealCallback {
                    override fun onBestMealReceived(mealResponse: List<Meal>) {
                        val mealList = ArrayList<Meal>()
                        for (response in mealResponse) {
                            val meal = Meal(
                                response.idMeal,
                                response.strMeal,
                                response.strMealThumb,
                                response.strInstructions
                            )
                            mealList.add(meal)
                        }
                        mealResults.postValue(mealList)
                    }

                })
        }
        return mealResults
    }

    override fun getDetailMeal(idMeal: String): LiveData<List<Meal>> {
        val detailResults = MutableLiveData<List<Meal>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailMeal(
                idMeal, object : RemoteDataSource.LoadDetailMealCallback {
                    override fun onDetailMealReceived(detailResponse: List<Meal>) {
                        val mealList = ArrayList<Meal>()
                        for (response in detailResponse) {
                            val detail = Meal(
                                response.idMeal,
                                response.strMeal,
                                response.strMealThumb,
                                response.strInstructions
                            )
                            mealList.add(detail)
                        }
                        detailResults.postValue(mealList)
                    }
                })
        }
        return detailResults
    }

}