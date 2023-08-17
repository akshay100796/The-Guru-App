package com.codexdroid.theguru.ui.fragments.centers

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.CentersAdapters
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.FragmentCentersBinding
import com.codexdroid.theguru.ui.activities.events_details.EventDetailsActivity
import com.codexdroid.theguru.ui.base.BaseFragment

/**
 * Copyright (C) [The-guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 17 August 2023, 11:07 PM
 * MH-15, India
 */

class CentersFragment : BaseFragment<FragmentCentersBinding, CenterViewModel>() {

    private val eventDetailsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override fun requestInitialised() {
        super.requestInitialised()

        CentersAdapters().apply {
            requestBinding().idRecyclerCenters.adapter = this

            requestOnClickListener(object : RecyclerItemClickListener{
                override fun onRecyclerItemClicked(position: Int, data: Any?, extra: String?) {
                    eventDetailsLauncher.launch(Intent(requireContext(), EventDetailsActivity::class.java))
                }
            })
        }
    }
}