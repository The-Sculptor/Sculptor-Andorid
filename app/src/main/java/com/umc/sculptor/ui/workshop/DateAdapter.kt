package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.api.DataX
import com.umc.sculptor.databinding.FragmentDetailWorkshopBinding
import com.umc.sculptor.databinding.ItemDateBinding

class DateAdapter(itemList: List<DataX>) :
    RecyclerView.Adapter<DateAdapter.ViewHolder>() {

    var datelist: ArrayList<DataX> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: ItemDateBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        val binding = FragmentDetailWorkshopBinding.bind(itemView)
        var layout = itemViewBinding.layoutDailyCheck
        var category = binding.iconCategory
        var dday = binding.tvDDay
        var powder = binding.tvStoneDustGram
        var startDate = binding.tvStartDate
        var stoneGoal = binding.tvGoal

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
        holder.category.text = datelist[position].category
        holder.dday.text = datelist[position].dday
        holder.powder.text = datelist[position].powder.toString()
        holder.startDate.text = datelist[position].startDate
        holder.stoneGoal.text = datelist[position].stoneGoal

        holder.layout.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }


    }

}
