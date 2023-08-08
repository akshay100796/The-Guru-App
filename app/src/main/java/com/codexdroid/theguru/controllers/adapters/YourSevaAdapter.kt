package com.codexdroid.theguru.controllers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.theguru.databinding.LayoutRecyclerYouSevaBinding

class YourSevaAdapter: RecyclerView.Adapter<YourSevaAdapter.YourSevaViewHolder>() {

    inner class YourSevaViewHolder(val binding: LayoutRecyclerYouSevaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourSevaViewHolder {
        return YourSevaViewHolder(LayoutRecyclerYouSevaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: YourSevaViewHolder, position: Int) {}
}