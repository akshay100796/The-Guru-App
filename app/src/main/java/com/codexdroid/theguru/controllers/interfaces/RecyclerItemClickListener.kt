package com.codexdroid.theguru.controllers.interfaces

interface RecyclerItemClickListener {
    fun onRecyclerItemClicked(position: Int, data: Any?, extra:String? = null)
}