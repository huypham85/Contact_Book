package com.example.contactbook

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragmentManager:FragmentManager, behavior :Int): FragmentStatePagerAdapter(fragmentManager,behavior) {
    override fun getCount(): Int {
        return 3 // tra ve so luong tab cua bottom navigation
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                return RecentFragment()
            }
            1->{
                return ContactFragment()
            }
            2->{
                return DialFragment()
            }
            else ->{
                return ContactFragment()
            }
        }
    }
}