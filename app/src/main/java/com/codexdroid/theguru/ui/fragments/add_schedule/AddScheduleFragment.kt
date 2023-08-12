package com.codexdroid.theguru.ui.fragments.add_schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.codexdroid.theguru.R
import com.codexdroid.theguru.databinding.FragmentAddScheduleBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 11 August 2023, 10:09 PM
 * MH-15, India
 */

class AddScheduleFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddScheduleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_schedule, container, false)

        return binding.root
    }


    override fun onStart() {
        super.onStart()
        dialog?.window?.setWindowAnimations(R.style.style_bottom_sheet)
    }

    override fun onStop() {
        dialog?.window?.setWindowAnimations(R.style.style_bottom_sheet)
        super.onStop()
    }

//    override fun getTheme(): Int {
//        return R.style.style_bottom_sheet
//    }
}