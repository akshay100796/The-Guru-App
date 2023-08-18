package com.codexdroid.theguru.controllers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.theguru.controllers.data_models.local.Members
import com.codexdroid.theguru.databinding.LayoutRecyclerMembersBinding

class MembersAdapter(private val members: List<Members>): RecyclerView.Adapter<MembersAdapter.MembersViewHolder>() {

    inner class MembersViewHolder(val binding: LayoutRecyclerMembersBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembersViewHolder {
        return MembersViewHolder(LayoutRecyclerMembersBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int = members.size

    override fun onBindViewHolder(holder: MembersViewHolder, position: Int) {
        holder.binding.apply {
            val member = members[position]
            idName.text = member.name
            idEmail.text = member.email
            idPhone.text = member.mobile
            idTextMemberType.text = member.type
        }
    }
}