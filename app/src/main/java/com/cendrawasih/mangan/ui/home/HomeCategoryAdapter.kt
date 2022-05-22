package com.cendrawasih.mangan.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cendrawasih.mangan.R
import com.cendrawasih.mangan.data.source.remote.response.Category
import com.cendrawasih.mangan.databinding.ItemCategoryBinding

class HomeCategoryAdapter(private val callback: HomeFragmentCallback) :
    RecyclerView.Adapter<HomeCategoryAdapter.CategoryViewHolder>() {
    private var listCategory = ArrayList<Category>()

    fun setCategory(category: List<Category>?) {
        if (category == null) return
        this.listCategory.clear()
        this.listCategory.addAll(category)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val itemsCategoryBinding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemsCategoryBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = listCategory[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = listCategory.size

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            with(binding) {
                Glide.with(itemView.context).load(category.strCategoryThumb).transform(
                    RoundedCorners(20)
                ).apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                ).into(imgCategory)
                itemView.setOnClickListener { callback.onDetailCategoryClick(category) }
            }
        }
    }
}