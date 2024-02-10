package com.umc.sculptor.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umc.sculptor.R
import com.umc.sculptor.data.model.dto.Alarm
import com.umc.sculptor.data.model.dto.FriendStatue
import com.umc.sculptor.data.model.remote.home.DataX
import com.umc.sculptor.databinding.ItemAlarmBinding
import com.umc.sculptor.databinding.ItemFriendStatueCardBinding
import com.umc.sculptor.databinding.ItemSearchProfileBinding

class SearchAdapter(itemList: List<DataX>, private val context: Context) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var alarmList: List<DataX> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // 아이템 클릭 리스너 인터페이스
    interface OnItemClickListener {
        fun onItemClick(id: String)
    }

    private var onItemClickListener: OnItemClickListener? = null

    // 클릭 리스너 설정 메서드
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class ViewHolder(itemViewBinding: ItemSearchProfileBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        var nickname = itemViewBinding.tvProfileNickname
        var name = itemViewBinding.tvName
        var profileImg = itemViewBinding.ivSearchProfileImg
        var layout = itemViewBinding.layoutSearch
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

        holder.nickname.text = alarmList[position].username

        holder.layout.setOnClickListener {

            onItemClickListener?.onItemClick(alarmList[position].id)

        }

        Glide.with(context)
            .load(alarmList[position].profileUrl)
            .placeholder(R.drawable.ellipse) // 이미지 로딩 중에 표시될 placeholder 이미지
            .error(R.drawable.ellipse) // 이미지 로딩 실패 시 표시될 이미지
            .into(holder.profileImg)
    }

    fun updateItemList(newItemList: List<DataX>) {
        alarmList = newItemList
        notifyDataSetChanged() // 리스트 변경 후 뷰 새로고침
    }

}