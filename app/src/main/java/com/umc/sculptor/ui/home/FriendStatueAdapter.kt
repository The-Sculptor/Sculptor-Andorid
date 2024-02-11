package com.umc.sculptor.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.data.model.dto.FriendStatue
import com.umc.sculptor.data.model.remote.home.Data
import com.umc.sculptor.data.model.remote.home.Follwing
import com.umc.sculptor.databinding.ItemFriendStatueCardBinding

class FriendStatueAdapter(itemList: List<Data>) :
    RecyclerView.Adapter<FriendStatueAdapter.ViewHolder>() {

    var friendStatueList: List<Data> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // 아이템 클릭 리스너 인터페이스
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    // 클릭 리스너 설정 메서드
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }


    inner class ViewHolder(itemViewBinding: ItemFriendStatueCardBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        var friendName = itemViewBinding.tvStatueName
        var friendStone = itemViewBinding.layoutFriendStone
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
        holder.friendStone.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }

        holder.friendName.text = friendStatueList[position].nickname

    }

}