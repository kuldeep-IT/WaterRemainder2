package com.example.waterremainder

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.waterremainder.adapter.TabPagerAdapter
import com.example.waterremainder.databinding.ActivityMainBinding
import com.example.waterremainder.db.WaterDB
import com.example.waterremainder.fragments.HistoryFragment
import com.example.waterremainder.fragments.HomeFragment
import com.example.waterremainder.fragments.SettingsFragment
import com.example.waterremainder.repositories.WaterRepo
import com.example.waterremainder.utils.DataStoreManager
import com.example.waterremainder.utils.PreferenceKeys
import com.example.waterremainder.viewmodel.WaterViewModel
import com.example.waterremainder.viewmodel.WaterVmProv
import com.example.waterremainder.walkThrough.MainWalkThrough
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedEditor: SharedPreferences.Editor

    lateinit var vm: WaterViewModel

    lateinit var dataStoreManager: DataStoreManager
    var isFirstRun: Boolean = false

    lateinit var adapter: TabPagerAdapter
    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repo = WaterRepo(WaterDB(this))
        val viewModelProviderFactory = WaterVmProv(repo)
        vm =  ViewModelProvider(this,viewModelProviderFactory).get(WaterViewModel::class.java)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        sharedEditor = sharedPreferences.edit()
        dataStoreManager = DataStoreManager(applicationContext)

      /*  if (isItFirstRun()) {
            startActivity(Intent(this@MainActivity, MainWalkThrough::class.java))
            finish()
        }*/

        setTabPagerAdapter()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setTabPagerAdapter(){
        adapter = TabPagerAdapter(this@MainActivity)

        adapter.addFragment(HomeFragment.newInstance().apply {

        }, "Home")

        adapter.addFragment(HistoryFragment.newInstance().apply {

        }, "History")

        adapter.addFragment(SettingsFragment.newInstance().apply {

        }, "Settings")

        binding.viewPager2.adapter = adapter
        binding.viewPager2.currentItem = 0
        TabLayoutMediator(binding.tabs, binding.viewPager2){ tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()

        binding.tabs.setSelectedTabIndicatorColor(getColor(R.color.white))

    }

    fun isItFirstRun(): Boolean {
        if(sharedPreferences.getBoolean("isFirstRun", true)){
            sharedEditor.putBoolean("isFirstRun", false)
            sharedEditor.commit()
            sharedEditor.apply()
            return true
        } else {
            return false
        }
    }

}