package com.codexdroid.theguru.controllers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.LayoutRecyclerYouSevaBinding

class YourSevaAdapter: RecyclerView.Adapter<YourSevaAdapter.YourSevaViewHolder>() {

    private lateinit var recyclerItemClickListener: RecyclerItemClickListener

    fun requestOnRecyclerClicked(listener: RecyclerItemClickListener) {
        recyclerItemClickListener = listener
    }

    inner class YourSevaViewHolder(val binding: LayoutRecyclerYouSevaBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourSevaViewHolder {
        return YourSevaViewHolder(LayoutRecyclerYouSevaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: YourSevaViewHolder, position: Int) {
        holder.binding.apply {

            idCheckBox.setOnCheckedChangeListener { _, isChecked ->
                recyclerItemClickListener.onRecyclerItemClicked(position,null, if(isChecked) "1" else "0")
            }

        }
    }
}