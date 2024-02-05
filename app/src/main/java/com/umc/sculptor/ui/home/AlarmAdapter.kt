package com.umc.sculptor.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.data.model.dto.Alarm
import com.umc.sculptor.data.model.dto.FriendStatue
import com.umc.sculptor.databinding.ItemAlarmBinding
import com.umc.sculptor.databinding.ItemFriendStatueCardBinding

class AlarmAdapter(itemList: ArrayList<Alarm>) :
    RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    var alarmList: ArrayList<Alarm> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: ItemAlarmBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        var alarmText = itemViewBinding.tvAlarmText
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemAlarmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = alarmList.size

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.alarmText.text = alarmList[position].text
    }

}