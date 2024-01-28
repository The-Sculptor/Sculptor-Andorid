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
import com.umc.sculptor.databinding.ItemSearchProfileBinding

class SearchAdapter(itemList: ArrayList<Alarm>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var alarmList: ArrayList<Alarm> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: ItemSearchProfileBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        var nickname = itemViewBinding.tvProfileNickname
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemSearchProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = alarmList.size

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.nickname.text = alarmList[position].text
    }

}