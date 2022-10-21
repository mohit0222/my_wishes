package com.example.mywishes.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
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
        val view = binding.root
        setContentView(view)
        logout()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_home -> startNavigationFlow(Home.HOME)
                R.id.ic_friends -> startNavigationFlow(Home.FRIENDS)
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
        val graph: NavGraph = inflater.inflate(R.navigation.nav_bottom_menu_flow)
        when (type) {
            Home.HOME -> graph.setStartDestination(R.id.fragment_home)
            Home.FRIENDS -> graph.setStartDestination(R.id.fragment_profile)

        }
        navHostFragment.navController.graph = graph
    }
}