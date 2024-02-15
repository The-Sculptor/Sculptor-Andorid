package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.databinding.ItemBoxBinding
import com.umc.sculptor.databinding.ItemDateBinding

class DateAdapter(itemList: ArrayList<Date>) :
    RecyclerView.Adapter<DateAdapter.ViewHolder>() {

    var datelist: ArrayList<Date> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: ItemDateBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        val layout = itemViewBinding.layoutDailyCheck
        val tvNumber = itemViewBinding.tvNumber
        val tvAchievement = itemViewBinding.tvAchievement
        val tv_4 = itemViewBinding.tv4
        val iv_4 = itemViewBinding.iv4
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
        holder.tvAchievement.text = datelist[position].tv_achievement
        holder.iv_4.setImageResource(datelist[position].iv_4 )
        holder.tv_4.text = datelist[position].tv_4
        holder.tvNumber.text = datelist[position].tv_number



        holder.layout.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }


    }

}
