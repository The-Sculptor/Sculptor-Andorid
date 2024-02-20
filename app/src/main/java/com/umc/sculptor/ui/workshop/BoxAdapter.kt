package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.DataXXX
import com.umc.sculptor.databinding.ItemBoxBinding


class BoxAdapter(itemList: List<DataXXX>) :
    RecyclerView.Adapter<BoxAdapter.ViewHolder>() {


    var datalist: List<DataXXX> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var selectedCategory:String = ""


    inner class ViewHolder(itemViewBinding: ItemBoxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
            val goalcardLayout = itemViewBinding.layoutGoalCard
            val  ddayTextView: TextView = itemViewBinding.tvDDay
            val startDateTextView: TextView = itemViewBinding.tvDateItem
            val stoneGoalTextView: TextView = itemViewBinding.tvDDay2

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
        fun onItemClick(id: String)
    }

    private var onItemClickListener: OnItemClickListener? = null

    // 클릭 리스너 설정 메서드
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun getItemCount(): Int = datalist.size

    override fun onBindViewHolder(
        holder: ViewHolder ,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val currentItem = datalist[position]

        if (selectedCategory.isEmpty() || currentItem.category == selectedCategory){
            holder.ddayTextView.text = datalist[position].dday
            holder.startDateTextView.text = datalist[position].startDate.take(10)
            holder.stoneGoalTextView.text =datalist[position].stoneName

            val context = holder.itemView.context
            val backgroundColorSpan: Int
            val textColor: Int

            // 카테고리에 따라 배경색과 텍스트 색상 설정
            when (currentItem.category) {
                "WORKOUT" -> holder.goalcardLayout.setBackgroundResource(R.drawable.workshop_back_orange)
                "STUDY"-> holder.goalcardLayout.setBackgroundResource(R.drawable.workshop_back_black)
                "DAILY"-> holder.goalcardLayout.setBackgroundResource(R.drawable.workshop_back_gray)
                else -> R.drawable.gray
            }

            textColor = when (currentItem.category) {
                "WORKOUT" -> ContextCompat.getColor(context, R.color.black)
                "STUDY" -> ContextCompat.getColor(context, R.color.main_orange)
                "DAILY" -> ContextCompat.getColor(context, R.color.white)
                else -> ContextCompat.getColor(context, R.color.white)
            }

            // 배경색과 텍스트 색상을 설정한 후 ViewHolder에 적용
            holder.ddayTextView.setTextColor(textColor)
            holder.startDateTextView.setTextColor(textColor)
            holder.stoneGoalTextView.setTextColor(textColor)

        }

        holder.goalcardLayout.setOnClickListener {
            onItemClickListener?.onItemClick(datalist[position].stoneId)
        }


    }



}


