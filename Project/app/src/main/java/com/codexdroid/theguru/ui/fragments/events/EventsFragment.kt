package com.codexdroid.theguru.ui.fragments.events

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.codexdroid.theguru.databinding.FragmentEventsBinding
import com.codexdroid.theguru.ui.activities.create_event.CreateEventActivity
import com.codexdroid.theguru.ui.base.BaseFragment

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 13 August 2023, 12:05 AM
 * MH-15, India
 */

class EventsFragment : BaseFragment<FragmentEventsBinding, EventsViewModel>() {


    private val newEventLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { /**api call**/}

    override fun requestInitialised() {
        super.requestInitialised()
        /**api call*/
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
        requestBinding().idButtonCreateEvent.setOnClickListener {
            newEventLauncher.launch(Intent(requireContext(),CreateEventActivity::class.java))
        }
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }

}