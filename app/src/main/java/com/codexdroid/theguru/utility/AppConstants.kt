package com.codexdroid.theguru.utility

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 31 July 2023, 11:06 PM
 * MH-15, India
 */

object AppConstants {

    object DateFormats {
        const val DD_MM_YYYY = "dd-MM-yyyy"
        const val DD_MMMM_YYYY = "dd MMMM yyyy"
        const val DD_MM_YYYY_HH_MM_A = "dd-MMM-yy hh:mm a"
        const val YYYY = "yyyy"
        const val MM = "MM"
        const val DD = "dd"
    }
    object Preferences {
        const val PREF_NAME = "pref_the_guru"
        const val TOKEN = "token"
    }

    object LINKS {
        const val LEARNINGS = "https://raw.githubusercontent.com/akshay100796/The-Guru-App-Notes/main/text/learnings.json"
    }

    object Errors {
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }

    object Firestore {
        const val COLLECTION_LOGINS = "logins"
        const val COLLECTION_USERS = "users"

        const val LOGIN_ADMIN_EMAIL = "email"
        const val LOGIN_ADMIIN_PASSWORD = "password"
    }
}