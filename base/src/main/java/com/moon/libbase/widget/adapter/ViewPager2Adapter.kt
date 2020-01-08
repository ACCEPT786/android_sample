package com.moon.libbase.widget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*

/**
 * @author ry
 * @date 2019-05-23
 */
class ViewPager2Adapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    private val arrayList = ArrayList<Fragment>()

    fun addFragment(pageItem: Fragment) {
        arrayList.add(pageItem)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun createFragment(position: Int): Fragment {
        return arrayList[position]
    }
}
