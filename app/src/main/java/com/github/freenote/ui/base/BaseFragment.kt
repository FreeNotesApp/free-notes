package com.github.freenote.ui.base

import androidx.fragment.app.Fragment

open class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    protected fun setToolbarTitle(resId: Int) {
        (requireActivity() as Contract).setToolbarTitle(resId)
    }

    interface Contract {
        fun setToolbarTitle(resId: Int)
    }
}