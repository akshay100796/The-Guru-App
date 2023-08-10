package com.codexdroid.theguru.controllers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.theguru.databinding.LayoutRecyclerSchedulesBinding

class ScheduleEventsAdapter : RecyclerView.Adapter<ScheduleEventsAdapter.ScheduleViewHolder>() {

    inner class ScheduleViewHolder(val binding: LayoutRecyclerSchedulesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(LayoutRecyclerSchedulesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.binding.apply {}
    }

    override fun getItemCount(): Int = 10
}