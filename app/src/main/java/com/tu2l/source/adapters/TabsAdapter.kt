package com.tu2l.source.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TabsAdapter(
    fm: FragmentManager,
    private val tabNames: List<String>,
    private val fragments: List<Fragment>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(i: Int): Fragment {
        return fragments[i]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }

    fun getPositionByTitle(title: String): Int {
        return tabNames.indexOf(title)
    }
}