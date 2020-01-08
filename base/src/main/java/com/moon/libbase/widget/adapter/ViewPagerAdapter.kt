package com.moon.libbase.widget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

/**
 * @author ry
 * @date 2019-05-23
 */
class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val arrayList = ArrayList<PagerItem>()
    override fun getItem(position: Int): Fragment {
        return arrayList[position].fragment
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    fun addFragment(pageItem: PagerItem) {
        arrayList.add(pageItem)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return arrayList[position].title
    }
}

data class PagerItem(var fragment: Fragment, var title: String? = null)