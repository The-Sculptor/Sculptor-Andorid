package com.umc.sculptor.ui.museum

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.data.model.remote.home.Data
import com.umc.sculptor.data.model.remote.museum.StoneX
import com.umc.sculptor.databinding.ItemMuseumEditSculptorBinding
import com.umc.sculptor.databinding.ItemMuseumSculptorCommentBinding

class MuseumEditRVAdapter(editList: ArrayList<StoneX>):

RecyclerView.Adapter<MuseumEditRVAdapter.ViewHolder>() {

    var editList: ArrayList<StoneX> = editList
        set(value) {
            field = value
            notifyDataSetChanged()
        }
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

    // 아이템 클릭 리스너 인터페이스
    interface OnItemClickListener {
        fun onItemClick(id: String)
    }

    private var onItemClickListener: OnItemClickListener? = null

    // 클릭 리스너 설정 메서드
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
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
        holder.editDay.text = editList[position].dDay
        holder.editTitle.text = editList[position].name
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
            onItemClickListener?.onItemClick(editList[position].id)

            // 아이템 제거
            editList.removeAt(holder.adapterPosition)
            // 변경된 데이터를 Adapter에게 알림
            notifyItemRemoved(holder.adapterPosition)


        }

    }

    override fun getItemCount(): Int = editList.size
}

