package com.codexdroid.theguru.controllers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codexdroid.theguru.controllers.data_models.local.Seva
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.LayoutRecyclerSevasBinding
import com.codexdroid.theguru.utility.AppConstants

class AssignSevaAdapter: RecyclerView.Adapter<AssignSevaAdapter.AssignSevaViewHolder>() {


    private lateinit var recyclerItemClickListener: RecyclerItemClickListener
    private val sevas by lazy { mutableListOf<Seva>() }

    fun requestSetOnClickListener(listener: RecyclerItemClickListener) {
        recyclerItemClickListener = listener
    }

    fun requestAddItem(seva: Seva) {
        sevas.add(seva)
        notifyItemInserted(sevas.lastIndex)
    }

    fun requestUpdate(position: Int, seva: Seva) {
        sevas[position] = seva
        notifyItemInserted(position)
    }

    fun requestDelete(position: Int) {
        sevas.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class AssignSevaViewHolder(val binding: LayoutRecyclerSevasBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignSevaViewHolder {
        return AssignSevaViewHolder(LayoutRecyclerSevasBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AssignSevaViewHolder, position: Int) {
        holder.binding.apply {
            idCheckBox.isChecked = sevas[position].isDone
            idTextSeva.text = sevas[position].title
            idTextPersonName.text = sevas[position].assignTo
            idTextRespond.text = sevas[position].response

            idTextEdit.setOnClickListener {
                recyclerItemClickListener.onRecyclerItemClicked(position,sevas[position],AppConstants.RecyclerView.ITEM_EDIT)
            }
            idTextDelete.setOnClickListener {
                recyclerItemClickListener.onRecyclerItemClicked(position,sevas[position],AppConstants.RecyclerView.ITEM_DELETE)
            }
        }
    }

    override fun getItemCount(): Int = sevas.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position
}