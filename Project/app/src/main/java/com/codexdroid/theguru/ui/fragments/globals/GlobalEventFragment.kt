package com.codexdroid.theguru.ui.fragments.globals

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import com.codexdroid.theguru.controllers.adapters.GuruLearningsAdapter
import com.codexdroid.theguru.controllers.adapters.UpcomingEventsAdapter
import com.codexdroid.theguru.controllers.data_models.local.Events
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.FragmentGlobalBinding
import com.codexdroid.theguru.di.room.tables.TableLearnings
import com.codexdroid.theguru.ui.activities.events_details.EventDetailsActivity
import com.codexdroid.theguru.ui.base.BaseFragment
import com.codexdroid.theguru.utility.AppConstants
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
class GlobalEventFragment : BaseFragment<FragmentGlobalBinding, GlobalViewModel>() {

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
        requestBinding().idImageClearText.setOnClickListener { requestViewModel().requestUpdateUi(AppConstants.UI.RESET) }
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()

        requestViewModel().updateUi.observe(viewLifecycleOwner) {
            when (it) {
                AppConstants.UI.SEARCH -> {
                    requestBinding().apply {}
                }
                AppConstants.UI.RESET -> {
                    requestBinding().apply {}
                }
                else -> {}
            }
        }
    }

    private fun requestShowLearningsAdapter() {
        val learnings = listOf (
            TableLearnings(2,"Assurance in being","Guru Learnings"),
            TableLearnings(2,"Follow the path of heart","Guru Learnings"),
            TableLearnings(2,"Experience the power within","Kundalini Shakti")
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

        val next2Days  = 1696876200000
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
