package com.example.contactbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.example.contactbook.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragment)
        binding.bottomNav.setupWithNavController(navController)
//        setUpViewPager()
//        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.action_recent -> {
//                    binding.viewPager.setCurrentItem(0)
//                }
//                R.id.action_contact -> {
//                    binding.viewPager.setCurrentItem(1)
//                }
//                R.id.action_dialpad -> {
//                    binding.viewPager.setCurrentItem(2)
//                }
//            }
//            true
//        }
//    }
//    private fun setUpViewPager(){
//        var viewPagerAdapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
//        binding.viewPager.setAdapter(viewPagerAdapter)
////        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
////
////            override fun onPageScrollStateChanged(state: Int) {
////            }
////
////            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
////
////            }
////            override fun onPageSelected(position: Int) {
////
////            }
////
////        })
    }
}