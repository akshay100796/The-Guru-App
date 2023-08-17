package com.codexdroid.theguru.controllers.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.data_models.local.HomeMenu
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.LayoutRecyclerHomeMenuBinding

class HomeMenuAdapters(private val context: Context, private var homeMenu: List<HomeMenu>): RecyclerView.Adapter<HomeMenuAdapters.HomeMenuViewHolder>() {

    private lateinit var recyclerItemClickListener: RecyclerItemClickListener
    private val colorWhite by lazy { ResourcesCompat.getColor(context.resources, R.color.white,null) }
    private val colorYellow by lazy { ResourcesCompat.getColor(context.resources, R.color.color_yellow_600,null) }

    fun requestOnRecyclerItemClicked(listener: RecyclerItemClickListener) {
        recyclerItemClickListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun requestRefreshList(list: List<HomeMenu>) {
        this.homeMenu = list
        notifyDataSetChanged()
    }

    fun requestUpdateSelection(isSelected: Boolean, position: Int) {
        homeMenu[position].isSelected = isSelected
        notifyItemChanged(position)
    }

    inner class HomeMenuViewHolder(val binding: LayoutRecyclerHomeMenuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMenuViewHolder {
        return HomeMenuViewHolder(LayoutRecyclerHomeMenuBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: HomeMenuViewHolder, position: Int) {
        holder.binding.apply {
            val menu = homeMenu[position]
            idIcon.setImageDrawable(menu.requestDrawable())
            idTextMenu.text = menu.requestTitle()

            if(menu.isSelected) {
                idIcon.imageTintList = ColorStateList.valueOf(colorYellow)
                idTextMenu.setTextColor(colorYellow)
            } else {
                idIcon.imageTintList = ColorStateList.valueOf(colorWhite)
                idTextMenu.setTextColor(colorWhite)
            }

            idContainer.setOnClickListener {
                recyclerItemClickListener.onRecyclerItemClicked(position, menu, null)
            }
        }
    }

    override fun getItemCount(): Int = homeMenu.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position
}