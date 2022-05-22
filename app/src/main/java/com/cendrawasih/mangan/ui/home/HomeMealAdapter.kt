package com.cendrawasih.mangan.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cendrawasih.mangan.R
import com.cendrawasih.mangan.data.source.remote.response.Meal
import com.cendrawasih.mangan.databinding.ItemMealBinding

class HomeMealAdapter(private val callback: HomeFragmentCallback) :
    RecyclerView.Adapter<HomeMealAdapter.MealViewHolder>() {
    private var listMeal = ArrayList<Meal>()

    fun setMeal(meal: List<Meal>?) {
        if (meal == null) return
        this.listMeal.clear()
        this.listMeal.addAll(meal)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealViewHolder {
        val itemMealBinding =
            ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(itemMealBinding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = listMeal[position]
        holder.bind(meal)
    }

    override fun getItemCount(): Int = listMeal.size

    inner class MealViewHolder(private val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            with(binding) {
                tvMeal.text = meal.strMeal
                Glide.with(itemView.context).load(meal.strMealThumb).apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                ).into(imgMeal)
                itemView.setOnClickListener { callback.onDetailMealClick(meal) }
            }
        }
    }
}