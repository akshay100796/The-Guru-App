package com.codexdroid.theguru.ui.activities.events_details

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.EventDetailsViewPagerAdapter
import com.codexdroid.theguru.databinding.ActivityEventDetailsBinding
import com.codexdroid.theguru.ui.activities.notification.NotificationActivity
import com.codexdroid.theguru.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 07 August 2023, 06:38 PM
 * MH-15, India
 */

@AndroidEntryPoint
class EventDetailsActivity : BaseActivity<ActivityEventDetailsBinding, EventsDetailsViewModel>() {

    private lateinit var adapter: EventDetailsViewPagerAdapter

    private val notificationLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override fun requestInitialised() {
        super.requestInitialised()

        adapter = EventDetailsViewPagerAdapter(supportFragmentManager,lifecycle)
        requestBinding().idViewpager.adapter = adapter
        TabLayoutMediator(requestBinding().idTabLayout,requestBinding().idViewpager) { tab, position ->
            tab.text = when (position) {
                0-> getString(R.string.schedules)
                1 -> getString(R.string.available)
                else -> getString(R.string.your_seva)
            }
        } .attach()
    }


    override fun requestSetUpListeners() {
        super.requestSetUpListeners()

        requestBinding().idImageNotification.setOnClickListener {
            notificationLauncher.launch(Intent(this@EventDetailsActivity, NotificationActivity::class.java))
        }
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }


}