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
    val mainListUser = mutableListOf<UserData>() // goi list user de duy tri du lieu
    init {
        mainListUser.add(UserData("Mr Thanh", "0987", "alo@123", "fb.com", R.drawable.anh_thanh ))
        mainListUser.add(UserData("Mr Kiet", "09871", "alo@1234", "fb.com", R.drawable.anh_kiet ))
        mainListUser.add(UserData("Ms Mai", "09872", "alo@1235", "fb.com", R.drawable.chi_mai ))
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