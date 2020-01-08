package com.moon.teachassistant

import com.alibaba.android.arouter.facade.annotation.Route
import com.moon.educational.ui.HomeFragment
import com.moon.libaccount.ui.fragment.MineFragment
import com.moon.libbase.widget.adapter.PagerItem
import com.moon.libbase.widget.adapter.ViewPagerAdapter
import com.moon.libcommon.base.BaseVMActivity
import com.moon.libcommon.utils.ARouteAddress
import com.moon.teachassistant.databinding.ActivityMainBinding
import com.moon.teachassistant.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = ARouteAddress.APP_MAIN)
class MainActivity : BaseVMActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_main


    override fun initView() {
        super.initView()
        val adapter = ViewPagerAdapter(supportFragmentManager)
        bottomNavigation.itemIconTintList = null
        viewpager.adapter = adapter
        viewpager.offscreenPageLimit = 3

        //todo 根据角色初始化界面
        initFinancialView(adapter)
    }


    /**
     * 校长界面
     */
    private fun initPrincipalView() {}

    /**
     * 财务教师界面
     */
    private fun initFinancialView(adapter: ViewPagerAdapter) {
        adapter.addFragment(PagerItem(HomeFragment()))
        adapter.addFragment(PagerItem(MineFragment()))
        adapter.addFragment(PagerItem(MineFragment()))
        adapter.notifyDataSetChanged()
        bottomNavigation.inflateMenu(R.menu.nav_bottom_financial)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_fragment -> viewpager.setCurrentItem(0, false)
                R.id.financial_fragment -> viewpager.setCurrentItem(1, false)
                R.id.mine_fragment -> viewpager.setCurrentItem(2, false)
            }
            true
        }
    }

    /**
     * 普通教师界面
     */
    private fun initTeacher(adapter: ViewPagerAdapter) {
        //todo adapter
        bottomNavigation.inflateMenu(R.menu.nav_bottom_teacher)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_fragment -> viewpager.setCurrentItem(0, false)
            }
            true
        }
    }
}
