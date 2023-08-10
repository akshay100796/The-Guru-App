package com.codexdroid.theguru.ui.fragments.create_event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.ScheduleEventsAdapter
import com.codexdroid.theguru.databinding.FragmentCreateEventBinding
import com.codexdroid.theguru.ui.base.BaseFragment
import com.google.android.material.datepicker.MaterialDatePicker

/**
 * Copyright (C) ERxPharmaPro - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 10 August 2023, 11:23 PM
 * MH-15, India
 */

class CreateEventFragment : BaseFragment<FragmentCreateEventBinding, CreateEventViewModel>() {

    override fun requestInitialised() {
        super.requestInitialised()
        requestScheduleEventsAdapter()
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }


    private fun requestScheduleEventsAdapter() {
        ScheduleEventsAdapter().apply {
            requestBinding().idRecyclerEvents.adapter = this
        }
    }

    private fun openCalenderForDate() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.select_event_date))
            .setTheme(R.style.datePickerTheme)
            .setSelection(MaterialDatePicker.thisMonthInUtcMilliseconds())
            .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
            .build()
        datePicker.addOnPositiveButtonClickListener {}
        datePicker.addOnCancelListener {}
        datePicker.show(parentFragmentManager,"JDatePicker")
    }


}