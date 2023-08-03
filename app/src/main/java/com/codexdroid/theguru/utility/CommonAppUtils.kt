package com.codexdroid.theguru.utility

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.codexdroid.theguru.R
import com.google.gson.GsonBuilder
import java.io.Serializable
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 31 July 2023, 11:07 PM
 * MH-15, India
 */

fun toJson(data: Any?): String? = GsonBuilder().serializeNulls().create().toJson(data)

fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun CharSequence?.isValidMobile(): Boolean {
    if (this?.length != 10) return false
    return  "^\\+?(91|0)?[7-9]\\d{9}$".toRegex().matches(this.toString())
}

fun CharSequence?.isValidPassword(): Boolean {
    var hasLowerCase = false
    var hasUpperCase = false
    var hasDigit = false

    this?.forEach {
        if (it in 'a'..'z') hasLowerCase = true
        if(it in 'A'..'Z') hasUpperCase = true
        if (it in '0'..'p') hasDigit = true
    }
    return hasLowerCase && hasUpperCase && hasDigit
}

fun showToast(context: Context, message: String, duration : Int = Toast.LENGTH_LONG) {
    Toast.makeText(context,message, duration).show()
}
fun requestDrawable(resources: Resources, icon: Int) : Drawable = ResourcesCompat.getDrawable(resources,icon,null)!!

fun requestHideKeyboard(context: Context, view: View) {
    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
        hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun requestYear(time: Long?) : Int = SimpleDateFormat(AppConstants.DateFormats.YYYY, Locale.getDefault()).format(time).toInt()
fun requestMonth(time: Long?) : Int = SimpleDateFormat(AppConstants.DateFormats.MM, Locale.getDefault()).format(time).toInt()
fun requestDateOfMonth(time: Long?) : Int = SimpleDateFormat(AppConstants.DateFormats.DD, Locale.getDefault()).format(time).toInt()
fun getDate(time: Long?): String = SimpleDateFormat(AppConstants.DateFormats.DD_MM_YYYY, Locale.getDefault()).format(time)
fun requestBirthDate(time: Long?) : String = SimpleDateFormat(AppConstants.DateFormats.DD_MMMM_YYYY, Locale.getDefault()).format(time)
fun requestCompleteDateTime(time: Long?) : String = SimpleDateFormat(AppConstants.DateFormats.DD_MM_YYYY_HH_MM_A, Locale.getDefault()).format(time)
fun requestAge(time: Long?) : String {
    LocalDate.of(requestYear(time), requestMonth(time), requestDateOfMonth(time)).apply {
        return "${LocalDate.now().year - year} year old"
    }
}

fun <T : Serializable?> requestSerializable(key:String, intent: Intent, mClass: Class<T>): T? {
    @Suppress("DEPRECATION")
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        intent.getSerializableExtra(key, mClass)!!
    else
        intent.getSerializableExtra(key) as T
}

fun requestShowImageInGlide(resources: Resources, imageView: AppCompatImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .error(ResourcesCompat.getDrawable(resources, R.drawable.ic_black_emoji_sad,null))
        .placeholder(ResourcesCompat.getDrawable(resources,R.drawable.drf_anim_loading,null))
        .into(imageView)
}

fun requestGeneratePassword(): String {

    val password = StringBuilder()

    repeat(2) {
        val randomCapitalChar = Random.nextInt(65..90).toChar()
        password.append(randomCapitalChar)

        val randomSmallChar = Random.nextInt(97..122).toChar()
        password.append(randomSmallChar)

        val randomNumber = Random.nextInt(48..57).toChar().code
        password.append(randomNumber)

        val randomSymbol = Random.nextInt(33..47).toChar()
        password.append(randomSymbol)
    }
    password.toString().toCharArray().shuffle()
    return password.toString()
}