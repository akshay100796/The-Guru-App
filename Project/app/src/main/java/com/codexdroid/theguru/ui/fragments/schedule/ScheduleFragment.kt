package com.codexdroid.theguru.ui.fragments.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.ScheduleAdapter
import com.codexdroid.theguru.databinding.FragmentScheduleBinding
import com.codexdroid.theguru.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 06 August 2023, 01:00 PM
 * MH-15, India
 */

 @AndroidEntryPoint
class ScheduleFragment : BaseFragment<FragmentScheduleBinding, ScheduleViewModel>() {

    override fun requestInitialised() {
        super.requestInitialised()

        requestShowSchedules()
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }

    private fun requestShowSchedules() {
        ScheduleAdapter().apply {
            requestBinding().idRecyclerSchedules.adapter = this
        }
    }
}