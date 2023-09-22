package com.example.image_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.image_search.Adapter.ViewPager2Adapter
import com.example.image_search.Search.Fragment_Search
import com.example.image_search.Locker.Fragment_locker
import com.example.image_search.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var tab1: Fragment_Search
    lateinit var tab2: Fragment_locker


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()


    }
    private fun initViewPager() {
        var viewPager2Adatper = ViewPager2Adapter(this)
        viewPager2Adatper.addFragment(Fragment_Search())
        viewPager2Adatper.addFragment(Fragment_locker())

        binding.mainViewpager.apply {
            adapter = viewPager2Adatper

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        TabLayoutMediator(binding.mainTabLayout, binding.mainViewpager) { tab, position ->
            Log.e("YMC", "ViewPager position: ${position}")
            when (position) {
                0 -> tab.text = "간식결과"
                1 -> tab.text = "내 보관함"
            }
        }.attach()
    }
}