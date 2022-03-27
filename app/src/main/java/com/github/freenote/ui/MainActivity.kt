package com.github.freenote.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.freenote.R
import com.github.freenote.data.repository.AppTheme
import com.github.freenote.databinding.ActivityMainBinding
import com.github.freenote.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), BaseFragment.Contract {

    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(vm.getAppTheme().toRes())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBarLayout)
    }

    override fun setToolbarTitle(resId: Int) {
        binding.toolBarLayout.title = resources.getString(resId)
    }

    private fun AppTheme.toRes(): Int {
        return when (this) {
            AppTheme.LIGHT -> R.style.Theme_Light
            AppTheme.DARKER -> R.style.Theme_Darker
            AppTheme.DARK -> R.style.Theme_Dark
        }
    }
}