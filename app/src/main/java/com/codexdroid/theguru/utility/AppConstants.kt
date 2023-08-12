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
        const val HH_MM_SS = "hh:mm:ss"
        const val YYYY = "yyyy"
        const val MM = "MM"
        const val DD = "dd"
    }
    object Preferences {
        const val PREF_NAME = "pref_the_guru"
        const val TOKEN = "token"
        const val TEMP_TOKEN = "Token 25hTABeb5DU"
        const val DATABASE_NAME = "TG_Database"
    }

    object LINKS {
        const val LEARNINGS = "https://raw.githubusercontent.com/akshay100796/The-Guru-App-Notes/main/text/learnings.json"
    }


    object HomeMenus {
        const val MENU_HOME = "menu_home"
        const val MENU_EVENTS = "menu_events"
        const val MENU_PAST_EVENTS = "menu_past_events"
        const val MENU_PROFILE = "menu_profile"

    }
    object Errors {
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val NAME = "name"
        const val WHATS_APP = "whats-app"
    }

    object Firestore {
        const val COLLECTION_LOGINS = "logins"
        const val COLLECTION_USERS = "users"

        const val LOGIN_ADMIN_EMAIL = "email"
        const val LOGIN_ADMIN_PASSWORD = "password"
    }

    object UserType {
        const val MEMBER = "Member"
        const val ADMIN = "Admin"
    }

    object UI {
        const val SEARCH = "ui_search"
        const val RESET = "ui_clear"
    }

    object SHEET {
        const val CREATE_EVENT = "sheet_events"
        const val UPDATE_SEVA = "update_seva"
        const val CREATE_SEVA = "create_seva"
        const val MAP = "map"
    }

    object RecyclerView {
        const val ITEM_ADD = "item_add"
        const val ITEM_EDIT = "item_edit"
        const val ITEM_DELETE = "item_delete"
        const val ITEM_CLICKED = "item_clicked"
        const val ITEM = "item"
        const val POSITION = "position"
    }
}