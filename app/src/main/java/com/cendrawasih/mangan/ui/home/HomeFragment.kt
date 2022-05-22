package com.cendrawasih.mangan.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cendrawasih.mangan.data.source.remote.response.Category
import com.cendrawasih.mangan.data.source.remote.response.Meal
import com.cendrawasih.mangan.databinding.FragmentHomeBinding
import com.cendrawasih.mangan.ui.detail.DetailMealActivity
import com.cendrawasih.mangan.viewmodel.ViewModelFactory


class HomeFragment : Fragment(), HomeFragmentCallback {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private lateinit var homeCategoryAdapter: HomeCategoryAdapter
    private lateinit var homeMealAdapter: HomeMealAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
            homeCategoryAdapter = HomeCategoryAdapter(this)
            homeMealAdapter = HomeMealAdapter(this)

            categoryObserved()
            mealObserved()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun categoryObserved() {
        showProgressBar()
        viewModel.getCategory().observe(viewLifecycleOwner) { category ->
            hideProgressBar()
            homeCategoryAdapter.setCategory(category)
            homeCategoryAdapter.notifyDataSetChanged()
        }

        binding?.rvCategory?.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            homeCategoryAdapter = HomeCategoryAdapter(this@HomeFragment)
            setHasFixedSize(true)
            adapter = homeCategoryAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun mealObserved() {
        showProgressBar()
        viewModel.getBestMeal("a").observe(viewLifecycleOwner) { meal ->
            hideProgressBar()
            homeMealAdapter.setMeal(meal)
            homeMealAdapter.notifyDataSetChanged()
        }

        binding?.rvMeal?.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            homeMealAdapter = HomeMealAdapter(this@HomeFragment)
            setHasFixedSize(true)
            adapter = homeMealAdapter
        }
    }

    private fun showProgressBar() {
        binding?.shimmerRecyclerView?.startShimmer()
        binding?.shimmerRecyclerView?.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding?.shimmerRecyclerView?.stopShimmer()
        binding?.shimmerRecyclerView?.visibility = View.GONE
    }

    override fun onDetailCategoryClick(category: Category) {

    }

    override fun onDetailMealClick(detailMeal: Meal) {
        startActivity(
            Intent(context, DetailMealActivity::class.java).putExtra(
                "meal_item",
                detailMeal.idMeal
            )
        )
    }

    override fun onResume() {
        super.onResume()
        categoryObserved()
        mealObserved()
    }
}