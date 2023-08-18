package com.codexdroid.theguru.ui.activities.members

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.CentersAdapters
import com.codexdroid.theguru.controllers.adapters.MembersAdapter
import com.codexdroid.theguru.controllers.data_models.local.Members
import com.codexdroid.theguru.databinding.ActivityCenterMembersBinding
import com.codexdroid.theguru.ui.base.BaseActivity
import com.codexdroid.theguru.ui.fragments.new_member.CreateMemberBottomSheetFragment
import com.codexdroid.theguru.utility.AppConstants

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 18 August 2023, 11:34 PM
 * MH-15, India
 */

class CenterMembersActivity : BaseActivity<ActivityCenterMembersBinding, CenterMembersViewModel>() {

    override fun requestInitialised() {
        super.requestInitialised()
        requestAdapter()
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
        requestBinding().idButtonAddMember.setOnClickListener {
            CreateMemberBottomSheetFragment().show(supportFragmentManager,AppConstants.SHEET.CREATE_MEMBER)
        }
    }

    private fun requestAdapter() {
        val list = listOf<Members>(
            Members("Akshay Pawar","akshay@gmail.com","+919988776655",AppConstants.UserType.ADMIN),
            Members("Test User 2", "test2@gmail.com","+919966338855"),
            Members("Test User 3", "test3@gmail.com","+919988774411"),
            Members("Test User 4", "test4@gmail.com","+918877554422"),
        )

        MembersAdapter(list).apply {
            requestBinding().idRecyclerMembers.adapter = this
        }
    }

}