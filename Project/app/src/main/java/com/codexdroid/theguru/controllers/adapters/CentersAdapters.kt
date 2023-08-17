package com.codexdroid.theguru.controllers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.LayoutRecyclerCentersBinding

class CentersAdapters: RecyclerView.Adapter<CentersAdapters.CenterViewHolder>() {

    private lateinit var recyclerClickListener: RecyclerItemClickListener

    fun requestOnClickListener(listener: RecyclerItemClickListener) {
        recyclerClickListener = listener
    }
    inner class CenterViewHolder(val binding: LayoutRecyclerCentersBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CenterViewHolder {
        return CenterViewHolder(LayoutRecyclerCentersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: CenterViewHolder, position: Int) {
        holder.binding.apply {
            idCardContainer.setOnClickListener {
                recyclerClickListener.onRecyclerItemClicked(position,null, null)
            }
        }
    }
}