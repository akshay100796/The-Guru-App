package com.codexdroid.theguru.utility

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 31 July 2023, 11:26 PM
 * MH-15, India
 */

class PrefManager @Inject constructor(@ApplicationContext var context: Context) {

    @Volatile
    var sharePref: SharedPreferences? = null
    @Volatile
    var prefManager: PrefManager? = null
    @Volatile
    var gson: Gson? = null

    init {
        initPrefIfNull()
    }

    companion object INSTANCE {
        fun getInstance(context: Context) : SharedPreferences? = context.getSharedPreferences(AppConstants.Preferences.PREF_NAME, Context.MODE_PRIVATE)
    }


    private fun getGsonInstance(): Gson? {
        if (gson == null) gson = Gson()
        return gson
    }

    private fun initPrefIfNull() {
        synchronized(context) {
            if (sharePref == null) {
                sharePref = context.getSharedPreferences(AppConstants.Preferences.PREF_NAME, Context.MODE_PRIVATE)
                sharePref?.edit()?.apply()
            }
        }
    }

    //Integer Data
    fun saveIntData(key: String, data: Int) { sharePref?.edit()?.putInt(key, data)?.apply() }
    fun getIntData(key: String): Int? = sharePref?.getInt(key, 0)

    //Long Data
    fun saveLongData(key: String, data: Long) { sharePref?.edit()?.putLong(key, data)?.apply() }
    fun getLongData(key: String): Long? = sharePref?.getLong(key, 0L)

    //Float Data
    fun saveFloatData(key: String, data: Float) { sharePref?.edit()?.putFloat(key, data)?.apply() }
    fun getFloatData(key: String): Float? = sharePref?.getFloat(key, 0.0F)

    //Boolean Data
    fun saveBooleanData(key: String, data: Boolean) { sharePref?.edit()?.putBoolean(key, data)?.apply() }
    fun getBooleanData(key: String): Boolean? = sharePref?.getBoolean(key, false)

    //String data
    fun saveStringData(key: String, data: String?) { sharePref?.edit()?.putString(key, data)?.apply() }
    fun getStringData(key: String): String? = sharePref?.getString(key, null)

    //Token
    fun saveToken(token: String) { sharePref?.edit()?.putString(AppConstants.Preferences.TOKEN, token)?.apply() }
    fun getToken(): String? = sharePref?.getString(AppConstants.Preferences.TOKEN, null)

    fun clearAll(){ sharePref?.edit()?.clear()?.apply() }


//    fun saveByteArray(byteArray: ByteArray) {
//        if(sharePref == null) initPrefIfNull()
//        val editor = sharePref?.edit()
//        val gson = getGsonInstance()
//        editor?.putString(AppConstants.PREF_BYTE_ARRAY,gson?.toJson(byteArray))
//        editor?.apply()
//    }
//
//    fun loadByteArray() : ByteArray? {
//        if(sharePref == null) initPrefIfNull()
//        val editor = sharePref?.edit()
//        val gson = getGsonInstance()
//        val json = sharePref?.getString(AppConstants.PREF_BYTE_ARRAY,null)
//        val type = object : TypeToken<ByteArray>(){}.type
//        val byteArray = gson?.fromJson<ByteArray>(json,type)
//        editor?.apply()
//        return byteArray
//    }
}