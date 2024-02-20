package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.Achieve
import com.umc.sculptor.data.model.remote.AchievementCounts
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
        var layout = itemViewBinding.layoutDailyCheck
        val num = itemViewBinding.tvNumber
        val text = itemViewBinding.tvAchievement
        val gram = itemViewBinding.tv4
        val icon = itemViewBinding.iv4
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
        holder.num.text = (datelist.size - position).toString()
        when(datelist[position].achieveStatus){
            "A"->{
                holder.text.text = "달성"
                holder.gram.text = "+10g"
                holder.icon.setImageResource(R.drawable.icon_circle)
            }
            "B"->{
                holder.text.text = "절반 달성"
                holder.gram.text = "+5g"
                holder.icon.setImageResource(R.drawable.icon_triangle)
            }
            "C"->{
                holder.text.text = "미달성"
                holder.gram.text = "+0g"
                holder.icon.setImageResource(R.drawable.icon_x)
            }
        }


    }

}
