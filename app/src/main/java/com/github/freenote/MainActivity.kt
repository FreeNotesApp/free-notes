package com.github.freenote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.freenote.databinding.ActivityMainBinding
import com.github.freenote.ui.base.BaseFragment

class MainActivity : AppCompatActivity(), BaseFragment.Contract {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBarLayout)
        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            binding.fragmentContainerView.id
        ) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun setToolbarTitle(resId: Int) {
        binding.toolBarLayout.title = resources.getString(resId)
    }

    override fun setToolbarVisibility(isVisible: Boolean) {
        binding.appBarLayout.isVisible = isVisible
    }

    override fun setBottomNavVisibility(isVisible: Boolean) {
        binding.bottomNavigationView.isVisible = isVisible
    }
}