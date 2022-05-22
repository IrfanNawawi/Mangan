package com.cendrawasih.mangan.ui.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cendrawasih.mangan.R
import com.cendrawasih.mangan.ui.akun.AkunFragment
import com.cendrawasih.mangan.ui.home.HomeFragment
import com.cendrawasih.mangan.ui.favorite.FavoriteFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.category, R.string.favorite, R.string.akun)
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> HomeFragment()
        1 -> FavoriteFragment()
        2 -> AkunFragment()
        else -> Fragment()
    }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(
        TAB_TITLES[position]
    )
}