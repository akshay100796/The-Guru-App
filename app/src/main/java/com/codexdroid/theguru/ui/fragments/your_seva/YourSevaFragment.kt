package com.codexdroid.theguru.ui.fragments.your_seva

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.YourSevaAdapter
import com.codexdroid.theguru.databinding.FragmentYourSevaBinding
import com.codexdroid.theguru.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 06 August 2023, 01:03 PM
 * MH-15, India
 */

@AndroidEntryPoint
class YourSevaFragment : BaseFragment<FragmentYourSevaBinding, YourSevaViewModel>() {

    override fun requestInitialised() {
        super.requestInitialised()
        requestShowYourSeva()
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }

    private fun requestShowYourSeva() {
        YourSevaAdapter().apply {
            requestBinding().idRecyclerYouSeva.adapter = this
        }
    }

}