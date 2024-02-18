package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.api.Data
import com.umc.sculptor.databinding.ItemBoxBinding


class BoxAdapter(itemList: ArrayList<Data>) :
    RecyclerView.Adapter<BoxAdapter.ViewHolder>() {

    var boxlist: ArrayList<Data> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var selectedCategory = 0

    fun selectedCategory(categoryIndex: Int) {
        selectedCategory = categoryIndex
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemViewBinding: ItemBoxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        val layout = itemViewBinding.layoutGoalCard
        val dday = itemViewBinding.tvDDay
        val dday2 = itemViewBinding.tvDDay2
        val dateitem = itemViewBinding.tvDateItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup ,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemBoxBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
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

    override fun onBindViewHolder(
        holder: ViewHolder ,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.dday.text = boxlist[position].dday
        holder.dday2.text = boxlist[position].stoneName
        holder.dateitem.text = boxlist[position].startDate

        holder.layout.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }

        val context = holder.itemView.context
        val backgroundColorSpan: Int
        val textColor: Int

        when (selectedCategory) {
            0 -> {
                // 전체 카테고리인 경우 운동, 공부, 기타를 번갈아가며 표시
                backgroundColorSpan = when (position % 3) {
                    1 -> R.drawable.orange // 운동
                    2 -> R.drawable.black // 공부
                    else -> R.drawable.gray // 기본
                }
                textColor = when (position % 3) {
                    1 -> ContextCompat.getColor(context, R.color.black) // 운동 텍스트 색상
                    2 -> ContextCompat.getColor(context, R.color.main_orange) // 공부 텍스트 색상
                    else -> ContextCompat.getColor(context, R.color.white) // 기본 텍스트 색상
                }
            }

            1 -> {
                backgroundColorSpan = R.drawable.orange
                textColor = ContextCompat.getColor(context , R.color.black)
            }

            2 -> {
                backgroundColorSpan = R.drawable.black
                textColor = ContextCompat.getColor(context , R.color.main_orange)
            }

            3 -> {
                backgroundColorSpan = R.drawable.gray
                textColor = ContextCompat.getColor(context , R.color.white)
            }

            else -> {
                backgroundColorSpan = R.drawable.gray
                textColor = ContextCompat.getColor(context , R.color.white)
            }
        }

    holder.layout.setBackgroundResource(backgroundColorSpan)
    holder.dday.setTextColor(textColor)
    holder.dday2.setTextColor(textColor)
    holder.dateitem.setTextColor(textColor)


}



}


