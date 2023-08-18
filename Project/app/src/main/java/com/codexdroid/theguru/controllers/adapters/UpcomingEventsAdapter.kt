package com.codexdroid.theguru.controllers.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.data_models.local.Events
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.LayoutRecyclerSatsangasBinding

class UpcomingEventsAdapter(private val context: Context)
    : RecyclerView.Adapter<UpcomingEventsAdapter.UpcomingViewHolder>() {

    private val gray300 by lazy { context.resources.getColor(R.color.color_gray_300,null) }
    private val gray500 by lazy { context.resources.getColor(R.color.color_gray_500,null) }

    private val events by lazy { mutableListOf<Events>() }
    private lateinit var timer: CountDownTimer
    private lateinit var recyclerItemClickListener: RecyclerItemClickListener

    fun requestItemClicked(listener: RecyclerItemClickListener) {
        recyclerItemClickListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun requestAddAllItems(items: MutableList<Events>) {
        events.addAll(items)
        notifyDataSetChanged()
    }

    inner class UpcomingViewHolder(val binding: LayoutRecyclerSatsangasBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        return UpcomingViewHolder(LayoutRecyclerSatsangasBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.binding.apply {
            val event = events[position]
            this.idLocation.text = event.place

            val diff = event.time - System.currentTimeMillis()
            if(diff > 0) {
                //Upcoming Event
                this.idEventHeldIn.text = context.getString(R.string.upcoming_event_in)
                idTextCountdownTimer.text = event.requestDateTime()
            } else {
                //Past Event
                idLinearContainer.setBackgroundColor(gray300)
                this.idEventHeldIn.text = context.getString(R.string.event_held_on)
                idTextCountdownTimer.text = context.getString(R.string.ago, event.requestDateTime())

            }

            this.idCardEvent.setOnClickListener {
                recyclerItemClickListener.onRecyclerItemClicked(position, event)
            }
        }
    }

    override fun getItemCount(): Int = events.size
}

