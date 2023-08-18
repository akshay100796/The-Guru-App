package com.codexdroid.theguru.controllers.data_models.local

import com.codexdroid.theguru.utility.AppConstants

data class Members (
    val name: String,
    val email: String,
    val mobile: String,
    val type: String = AppConstants.UserType.MEMBER
)