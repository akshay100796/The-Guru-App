package com.codexdroid.theguru.controllers.data_models.local

data class Seva (
    val title: String,
    val assignTo: String,
    val response:String = "",
    val isDone:Boolean = false
)