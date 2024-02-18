package com.umc.sculptor.ui.museum

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.databinding.ItemMuseumEditSculptorBinding
import com.umc.sculptor.databinding.ItemMuseumSculptorCommentBinding

class MuseumEditRVAdapter(private val editList: ArrayList<Edit>):

RecyclerView.Adapter<MuseumEditRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemMuseumEditSculptorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(val binding: ItemMuseumEditSculptorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val editDay = binding.editDayTxt
        val editTitle = binding.editTitleTxt
        val editLayout=binding.editLayout
        val editDelete=binding.museumEditDelete

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.editDay.text = editList[position].day
        holder.editTitle.text = editList[position].title
        var startX=0f
        holder.editLayout.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                }

                MotionEvent.ACTION_UP -> {
                    val endX = event.x
                    val distanceX = endX - startX

                    // 스와이프를 감지하기 위한 조건 설정
                    if (distanceX < 100) {

                        holder.editDelete.visibility=View.VISIBLE
                    }
                    if (distanceX > 100) {

                        holder.editDelete.visibility=View.GONE
                    }
                }
            }
                true // 이벤트 소비

        }
        holder.editDelete.setOnClickListener {
            // 아이템 제거
            editList.removeAt(holder.adapterPosition)
            // 변경된 데이터를 Adapter에게 알림
            notifyItemRemoved(holder.adapterPosition)
        }

    }

    override fun getItemCount(): Int = editList.size
}

