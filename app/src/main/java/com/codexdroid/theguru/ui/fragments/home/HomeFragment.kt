package com.codexdroid.theguru.ui.fragments.home

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.codexdroid.theguru.controllers.adapters.GuruLearningsAdapter
import com.codexdroid.theguru.controllers.adapters.UpcomingEventsAdapter
import com.codexdroid.theguru.controllers.data_models.local.Events
import com.codexdroid.theguru.controllers.data_models.local.Learnings
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.FragmentHomeBinding
import com.codexdroid.theguru.ui.activities.events_details.EventDetailsActivity
import com.codexdroid.theguru.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 06 August 2023, 12:56 PM
 * MH-15, India
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private lateinit var timer: Timer
    private lateinit var upcomingEventsAdapter: UpcomingEventsAdapter

    private val eventDetailsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override fun requestInitialised() {
        super.requestInitialised()
        upcomingEventsAdapter = UpcomingEventsAdapter(requireContext()).apply {
            upcomingEventsAdapter = this
            requestBinding().idRecyclerEvents.adapter = this
        }
        requestShowLearningsAdapter()
        requestShowEvents()

    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }

    private fun requestShowLearningsAdapter() {
        val learnings = listOf (
            Learnings("Assurance in being","Guru Learnings"),
            Learnings("Follow the path of heart","Guru Learnings"),
            Learnings("Experience the power within","Kundalini Shakti")
        )

        GuruLearningsAdapter(learnings).apply {
            requestBinding().idLearningsViewpager.adapter = this
            timer = Timer()
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    CoroutineScope(Dispatchers.Main).launch {
                        val totalItems = requestBinding().idLearningsViewpager.adapter?.count ?: 0
                        if(totalItems > 0) {
                            val next = (requestBinding().idLearningsViewpager.currentItem + 1) % totalItems
                            requestBinding().idLearningsViewpager.setCurrentItem(next,true)
                        }
                    }
                }
            },3000,3000)
        }
    }

    private fun requestShowEvents() {

        val next2Days  = 1691605800000
        val events = mutableListOf<Events>().apply {
            add(Events(next2Days,"Om Hall, Nashik, 422101"))
            add(Events(next2Days,"Shanti Hall, Nashik, 422101"))
            add(Events(next2Days,"Kalidas Hall, Nashik, 422101"))
            add(Events(next2Days,"Narmada Hall, Pune, 354555"))
            add(Events(next2Days,"Pushpaa Launce, Gujrat, 254535"))
        }
        upcomingEventsAdapter.apply {
            requestAddAllItems(events)
            requestItemClicked(object : RecyclerItemClickListener{
                override fun onRecyclerItemClicked(position: Int, data: Any?, extra: String?) {
                    eventDetailsLauncher.launch(Intent(requireContext(), EventDetailsActivity::class.java))
                }
            })
        }
    }
}
