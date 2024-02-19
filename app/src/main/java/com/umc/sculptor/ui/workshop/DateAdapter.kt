package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.data.model.remote.Achieve
import com.umc.sculptor.data.model.remote.DataX
import com.umc.sculptor.databinding.ItemDateBinding

class DateAdapter(itemList: List<Achieve>) :
    RecyclerView.Adapter<DateAdapter.ViewHolder>() {

    var datelist: List<Achieve> = itemList

        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: ItemDateBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        val achieve = itemViewBinding.tvAchievement
        val gram = itemViewBinding.tv4
        var layout = itemViewBinding.layoutDailyCheck
//        var category = binding.iconCategory
//        var dday = binding.tvDDay
//        var powder = binding.tvStoneDustGram
//        var startDate = binding.tvStartDate
//        var stoneGoal = binding.tvGoal

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemDateBinding.inflate(
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

    override fun getItemCount(): Int = datelist.size

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.achieve.text = datelist[position].achieveStatus
        holder.layout.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }


    }

}
