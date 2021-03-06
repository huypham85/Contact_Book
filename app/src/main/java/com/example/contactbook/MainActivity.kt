package com.example.contactbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.contactbook.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val mainListUser = ArrayList<UserData>() // goi list user de duy tri du lieu
    val recentListUser = ArrayList<UserData>()
    init {
        mainListUser.add(UserData("Mr Thanh", "0987", "alo@gmail.com", "fb.com", R.drawable.anhthanh ))
        mainListUser.add(UserData("Mr Kiet", "09871", "alo@gmail.com", "fb.com", R.drawable.anh_kiet ))
        mainListUser.add(UserData("Ms Mai", "09872", "alo@gmail.com", "fb.com", R.drawable.chimai ))
    }

//    private var layoutManager: RecyclerView.LayoutManager? =null
//    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragment)
        binding.bottomNav.setupWithNavController(navController)
    }

}