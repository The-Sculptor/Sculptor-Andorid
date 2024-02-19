package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.dto.WorkshopDetailViewModel
import com.umc.sculptor.data.model.remote.Achieve
import com.umc.sculptor.data.model.remote.AchievementCounts
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
        val iv4: ImageView = itemView.findViewById(R.id.iv_4)
        val tv4: TextView = itemView.findViewById(R.id.tv_4)

        val achieveStatus = itemViewBinding.iv4
        val achieveId = itemViewBinding.tvAchievement
        var layout = itemViewBinding.layoutDailyCheck
        val date = itemViewBinding.tvNumber

        fun bind(item: StonepowderFragment) {
            val achievementCounts = when (item.clickedButtonId) {
                R.id.icon_all -> AchievementCounts(a = 10,b = 5, c =0 )
                R.id.icon_mid -> AchievementCounts(a = 5 , b = 15 , c = 25)
                R.id.icon_none -> AchievementCounts(a = 0 , b = 10 , c = 20)
                else -> AchievementCounts(a = 0 , b = 0 , c = 0)
            }

            fun bind(item: Date) {
                tv4.text = "+" + (achievementCounts.a)
            }

            when (item.clickedButtonId) {
                R.id.icon_all -> {
                    iv4.setImageResource(R.drawable.icon_circle)
                }

                R.id.icon_mid -> {
                    iv4.setImageResource(R.drawable.icon_triangle)
                }

                R.id.icon_none -> {
                    iv4.setImageResource(R.drawable.icon_x)
                }

                else -> {
                    iv4.setImageResource(R.drawable.black)
                }
            }


        }
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
        holder.achieveStatus.setImageResource(R.drawable.icon_circle)
        holder.date.text = datelist[position].date
        holder.achieveId.text = datelist[position].achieveId
        holder.layout.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }


    }

}
