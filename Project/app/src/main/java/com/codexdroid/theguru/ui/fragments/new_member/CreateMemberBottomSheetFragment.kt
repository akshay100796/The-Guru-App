package com.codexdroid.theguru.ui.fragments.new_member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.codexdroid.theguru.R
import com.codexdroid.theguru.databinding.FragmentCreateMemberBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 19 August 2023, 12:07 AM
 * MH-15, India
 */

class CreateMemberBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCreateMemberBottomSheetBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_member_bottom_sheet, container, false)
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