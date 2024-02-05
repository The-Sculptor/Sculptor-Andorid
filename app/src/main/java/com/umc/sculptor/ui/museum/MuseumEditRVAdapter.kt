package com.umc.sculptor.ui.museum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.databinding.ItemMuseumEditSculptorBinding
import com.umc.sculptor.databinding.ItemMuseumSculptorCommentBinding

class MuseumEditRVAdapter(private val editList: ArrayList<Edit>):

RecyclerView.Adapter<MuseumEditRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemMuseumEditSculptorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(val binding: ItemMuseumEditSculptorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val editDay = binding.editDayTxt
        val editTitle = binding.editTitleTxt


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.editDay.text = editList[position].day
        holder.editTitle.text = editList[position].title
    }

    override fun getItemCount(): Int = editList.size
}

