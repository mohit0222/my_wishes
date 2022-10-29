package com.example.mywishes.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.mywishes.R
import com.example.mywishes.databinding.ActivityHomeBinding
import com.example.mywishes.preferences.PreferenceUtils
import com.example.mywishes.utilities.Home
import com.example.mywishes.utilities.gotoLogin

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logout()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_home -> startNavigationFlow(Home.HOME)
                R.id.ic_friends -> startNavigationFlow(Home.FRIENDS)
                R.id.ic_post -> startNavigationFlow(Home.POST)
                R.id.ic_setting -> startNavigationFlow(Home.SETTING)
            }
            true
        }
    }

    fun logout() {
        binding.btnLogout.setOnClickListener {
            PreferenceUtils.logout()
            gotoLogin(this)
        }
    }

    private fun startNavigationFlow(type: Int?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)
        val graph: NavGraph = inflater.inflate(R.navigation.nav_bottom_menu_flow)
        when (type) {
            Home.HOME -> graph.setStartDestination(R.id.fragment_home)
            Home.FRIENDS -> graph.setStartDestination(R.id.fragment_profile)
            Home.POST -> graph.setStartDestination(R.id.fragment_post)
            Home.SETTING -> graph.setStartDestination(R.id.fragment_setting)

        }
        navHostFragment.navController.graph = graph
    }

}