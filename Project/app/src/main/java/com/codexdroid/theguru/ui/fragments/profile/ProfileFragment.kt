package com.codexdroid.theguru.ui.fragments.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codexdroid.theguru.R
import com.codexdroid.theguru.databinding.FragmentProfileBinding
import com.codexdroid.theguru.ui.activities.members.CenterMembersActivity
import com.codexdroid.theguru.ui.base.BaseFragment
import com.codexdroid.theguru.utility.AppConstants
import dagger.hilt.android.AndroidEntryPoint

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 06 August 2023, 12:59 PM
 * MH-15, India
 */

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {


    override fun requestInitialised() {
        super.requestInitialised()
        requestBinding().idTextLatLong.text = getString(
            R.string.latitude_longitude,
            AppConstants.NUMBERS.LATITUDE,
            AppConstants.NUMBERS.LONGITUDE
        )
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()

        requestBinding().apply {
            idImageNavToMapContainer.setOnClickListener {
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("geo:0,0?q=${AppConstants.NUMBERS.LATITUDE} , ${AppConstants.NUMBERS.LONGITUDE}")
                    startActivity(this)
                }
            }
            idTextViewMembers.setOnClickListener {
                startActivity(Intent(requireContext(),CenterMembersActivity::class.java))
            }
        }
    }


}