package com.codexdroid.theguru.ui.fragments.update_seva

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.codexdroid.theguru.R
import com.codexdroid.theguru.databinding.FragmentUpdateSevaBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Copyright (C) ERxPharmaPro - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 09 August 2023, 10:24 PM
 * MH-15, India
 */

class UpdateSevaBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentUpdateSevaBottomSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_seva_bottom_sheet, container, false)

        binding.idButtonUpdateSeva.setOnClickListener { dismiss() }
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
}