package com.cendrawasih.mangan.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cendrawasih.mangan.databinding.ActivityDetailMealBinding
import com.google.android.material.snackbar.Snackbar

class DetailMealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}