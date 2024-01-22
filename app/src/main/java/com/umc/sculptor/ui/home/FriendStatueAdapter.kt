package com.umc.sculptor.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.data.model.dto.FriendStatue
import com.umc.sculptor.databinding.ItemFriendStatueCardBinding

class FriendStatueAdapter(itemList: ArrayList<FriendStatue>) :
    RecyclerView.Adapter<FriendStatueAdapter.ViewHolder>() {

    var friendStatueList: ArrayList<FriendStatue> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: ItemFriendStatueCardBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        var friendName = itemViewBinding.tvStatueName
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemFriendStatueCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = friendStatueList.size

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

    }

}