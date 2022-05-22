package com.cendrawasih.mangan.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cendrawasih.mangan.R
import com.cendrawasih.mangan.data.source.remote.response.Meal
import com.cendrawasih.mangan.databinding.ActivityDetailMealBinding
import com.cendrawasih.mangan.databinding.ContentDetailMealBinding
import com.cendrawasih.mangan.viewmodel.ViewModelFactory

class DetailMealActivity : AppCompatActivity() {

    private lateinit var binding: ContentDetailMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMealBinding = ActivityDetailMealBinding.inflate(layoutInflater)
        binding = activityDetailMealBinding.detailMeal

        setContentView(activityDetailMealBinding.root)

        setSupportActionBar(activityDetailMealBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMealViewModel::class.java]

        val mealItem = intent.getStringExtra("meal_item")
        binding.progressBar.visibility = View.VISIBLE
        if (mealItem != null) viewModel.getDetailMeal(mealItem).observe(this) { meal ->
            binding.progressBar.visibility = View.GONE
            meal.forEach {
                detailMeal(it)
            }
            Log.i("TAG", "dapet coy $meal")

        }
    }

    private fun detailMeal(meal: Meal) {
        binding.tvTitle.text = meal.strMeal
        binding.tvInstruction.text = meal.strInstructions
        Glide.with(this)
            .load(meal.strMealThumb)
            .transform(RoundedCorners(20)).apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
            ).into(binding.imgMeal)
    }
}