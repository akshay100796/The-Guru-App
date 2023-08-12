package com.codexdroid.theguru.ui.fragments.create_event

import androidx.navigation.fragment.NavHostFragment
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.ScheduleEventsAdapter
import com.codexdroid.theguru.databinding.FragmentCreateEventBinding
import com.codexdroid.theguru.ui.base.BaseFragment
import com.codexdroid.theguru.ui.fragments.add_schedule.AddScheduleFragment
import com.codexdroid.theguru.ui.fragments.create_event.CreateEventFragmentDirections
import com.codexdroid.theguru.utility.AppConstants
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
        requestBinding().idButtonAddSchedule.setOnClickListener {
            AddScheduleFragment().show(childFragmentManager, AppConstants.SHEET.CREATE_EVENT)
        }

        requestBinding().idButtonNext.setOnClickListener {
            val action = CreateEventFragmentDirections.actionIdFragmentCreateEventToIdFragmentAssignSeva()
            NavHostFragment.findNavController(this).navigate(action)
        }
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