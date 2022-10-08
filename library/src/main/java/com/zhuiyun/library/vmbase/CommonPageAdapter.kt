package com.zhuiyun.library.vmbase

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**

 * @author: yun

 * @date: 2022/10/8 0008 10

 */
class CommonPageAdapter(@NonNull fm: FragmentManager, behavior: Int, private var fragments: MutableList<Fragment>, private var tabList: List<String>) : FragmentPagerAdapter(fm, behavior) {

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabList[position]
    }
}