package com.github.freenote.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    open val shownToolbar: Boolean = true
    open val shownBottomNav: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as Contract).setToolbarVisibility(shownToolbar)
        (requireActivity() as Contract).setBottomNavVisibility(shownBottomNav)
    }

    protected fun setToolbarTitle(resId: Int) {
        (requireActivity() as Contract).setToolbarTitle(resId)
    }

    interface Contract {
        fun setToolbarTitle(resId: Int)
        fun setToolbarVisibility(isVisible: Boolean)
        fun setBottomNavVisibility(isVisible: Boolean)
    }
}