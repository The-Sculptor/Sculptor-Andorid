package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.umc.sculptor.databinding.ItemBoxBinding


class BoxAdapter(itemList: ArrayList<Box>) :
    RecyclerView.Adapter<BoxAdapter.ViewHolder>() {

    var boxlist: ArrayList<Box> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: ItemBoxBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
            val layout = itemViewBinding.layoutGoalCard
            val dday = itemViewBinding.tvDDay

        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemBoxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
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

    override fun getItemCount(): Int = boxlist.size

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.dday.text = boxlist[position].text

        holder.layout.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }


    }

}
