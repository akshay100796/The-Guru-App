package com.codexdroid.theguru.controllers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.theguru.databinding.LayoutRecyclerAvailableBinding

class AvailableAdapter : RecyclerView.Adapter<AvailableAdapter.AvailableViewHolder>() {

    inner class AvailableViewHolder(val binding: LayoutRecyclerAvailableBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableViewHolder {
        return AvailableViewHolder(LayoutRecyclerAvailableBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: AvailableViewHolder, position: Int) {
        holder.binding.apply {  }
    }

    override fun getItemCount(): Int  = 5

}