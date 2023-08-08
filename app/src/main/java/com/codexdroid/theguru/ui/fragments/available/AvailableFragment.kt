package com.codexdroid.theguru.ui.fragments.available

import com.codexdroid.theguru.controllers.adapters.AvailableAdapter
import com.codexdroid.theguru.databinding.FragmentAvailableBinding
import com.codexdroid.theguru.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 06 August 2023, 01:02 PM
 * MH-15, India
 */

@AndroidEntryPoint
class AvailableFragment : BaseFragment<FragmentAvailableBinding, AvailableViewModel>() {

    override fun requestInitialised() {
        super.requestInitialised()

        requestShowAvailable()
    }

    private fun requestShowAvailable() {
        AvailableAdapter().apply {
            requestBinding().idRecyclerAvailable.adapter = this
        }
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }

}