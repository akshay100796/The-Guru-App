package com.codexdroid.theguru.controllers.data_models.local

import android.graphics.drawable.Drawable

data class HomeMenu(
    private val menuName: String,
    private val icon: Drawable,
    private val menuType: String,
    var isSelected: Boolean = false
) {
    fun requestMenuType() = menuType
    fun requestDrawable() = icon
    fun requestTitle() = menuName
}