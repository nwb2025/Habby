package com.example.hubby.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyViewPagerAdapter(manager : FragmentManager ) : FragmentPagerAdapter(manager){


    private val frList : MutableList<Fragment> = ArrayList()
    private val titles:MutableList<String> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return frList[position]
    }

    override fun getCount(): Int {
        return frList.size
    }
    fun addFragment(fr: Fragment, title:String)
    {
        frList.add(fr)
        titles.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}