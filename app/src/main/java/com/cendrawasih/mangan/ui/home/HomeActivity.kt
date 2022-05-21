package com.cendrawasih.mangan.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cendrawasih.mangan.databinding.ActivityHomeBinding
import com.cendrawasih.mangan.ui.detail.DetailMealActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvHello.setOnClickListener {
            startActivity(Intent(this, DetailMealActivity::class.java))
        }
    }
}