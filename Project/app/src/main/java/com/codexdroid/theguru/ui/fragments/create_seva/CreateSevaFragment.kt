package com.codexdroid.theguru.ui.fragments.create_seva

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.data_models.local.Seva
import com.codexdroid.theguru.databinding.FragmentCreateSevaBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Copyright (C) ERxPharmaPro - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 11 August 2023, 10:36 PM
 * MH-15, India
 */

class CreateSevaFragment(val isForAdd: Boolean = true, val seva: Seva? = null) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCreateSevaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_seva, container, false)

        setUpListener()

        return binding.root
    }


    private fun setUpListener() {

    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setWindowAnimations(R.style.style_bottom_sheet)
    }

    override fun onStop() {
        dialog?.window?.setWindowAnimations(R.style.style_bottom_sheet)
        super.onStop()
    }
}