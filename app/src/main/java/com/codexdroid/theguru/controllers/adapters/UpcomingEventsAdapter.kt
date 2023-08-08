package com.codexdroid.theguru.controllers.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.theguru.controllers.data_models.local.Events
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.LayoutRecyclerSatsangasBinding

class UpcomingEventsAdapter(private val context: Context)
    : RecyclerView.Adapter<UpcomingEventsAdapter.UpcomingViewHolder>() {

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

            timer = object : CountDownTimer(event.time,1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val timeLeft = event.time - System.currentTimeMillis()

                     idTextCountdownTimer.text = event.requestTime(timeLeft)
                }
                override fun onFinish() {}
            }
            timer.start()

            this.idCardEvent.setOnClickListener {
                recyclerItemClickListener.onRecyclerItemClicked(position, event)
            }
        }
    }

    override fun getItemCount(): Int = events.size
}

