package com.codexdroid.theguru.controllers.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.codexdroid.theguru.ui.fragments.available.AvailableFragment
import com.codexdroid.theguru.ui.fragments.schedule.ScheduleFragment
import com.codexdroid.theguru.ui.fragments.your_seva.YourSevaFragment

class EventDetailsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle : Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ScheduleFragment()
            1 -> AvailableFragment()
            else -> YourSevaFragment()
        }
    }
}