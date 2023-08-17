package com.codexdroid.theguru.utility

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.codexdroid.theguru.R

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 31 July 2023, 11:24 PM
 * MH-15, India
 */

object Loader {

    private var dialog: Dialog? = null

    fun show (context: Context) {
        dialog = createLoader(context)
        dialog?.show()
    }

    fun hide() {
        if (dialog != null && dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }

    private fun createLoader(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.let {
            it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setContentView(R.layout.layout_progress_bar)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            return it
        }
    }
}