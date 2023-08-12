package com.codexdroid.theguru.ui.fragments.assign_seva


import com.codexdroid.theguru.controllers.adapters.AssignSevaAdapter
import com.codexdroid.theguru.controllers.data_models.local.Seva
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.FragmentAssignSevaBinding
import com.codexdroid.theguru.ui.base.BaseFragment
import com.codexdroid.theguru.ui.fragments.create_seva.CreateSevaFragment
import com.codexdroid.theguru.utility.AppConstants

/**
 * Copyright (C) ERxPharmaPro - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 10 August 2023, 11:22 PM
 * MH-15, India
 */


class AssignSevaFragment : BaseFragment<FragmentAssignSevaBinding, AssignSevaViewModel>() {

    override fun requestInitialised() {
        super.requestInitialised()
        requestShowAdapter()
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
        requestBinding().idButtonCreateSeva.setOnClickListener {
            CreateSevaFragment().show(childFragmentManager,AppConstants.SHEET.CREATE_SEVA)
        }
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }


    private fun requestShowAdapter() {

        AssignSevaAdapter().apply {
            requestBinding().recyclerView.adapter = this
            requestAddItem(Seva("Haar Gheun Yene","Akshay","YES"))
            requestAddItem(Seva("Min 1 Kg Prasad gheun yave, min 1kg ladu cha","Other Member","Not Possible"))
            requestAddItem(Seva("Audio and Video prepare karun yene without fail","Akshay Pawar","YES", true))

            requestSetOnClickListener(object : RecyclerItemClickListener{
                override fun onRecyclerItemClicked(position: Int, data: Any?, extra: String?) {
                    val sevaData = data as Seva
                    when (extra) {
                        AppConstants.RecyclerView.ITEM_EDIT -> {
                            CreateSevaFragment(isForAdd = false, seva = sevaData).show(childFragmentManager,AppConstants.SHEET.UPDATE_SEVA)
                        }
                        AppConstants.RecyclerView.ITEM_DELETE -> {
                            requestDelete(position)
                        }
                    }
                }
            })
        }
    }
}