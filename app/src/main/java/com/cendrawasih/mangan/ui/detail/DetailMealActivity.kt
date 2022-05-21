package com.cendrawasih.mangan.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cendrawasih.mangan.databinding.ActivityDetailMealBinding
import com.cendrawasih.mangan.databinding.ContentDetailMealBinding
import com.google.android.material.snackbar.Snackbar

class DetailMealActivity : AppCompatActivity() {

    private lateinit var detailContentBinding: ContentDetailMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMealBinding = ActivityDetailMealBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMealBinding.detailMeal

        setContentView(activityDetailMealBinding.root)

        setSupportActionBar(activityDetailMealBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}