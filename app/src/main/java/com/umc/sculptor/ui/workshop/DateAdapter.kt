package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.databinding.ItemBoxBinding
import com.umc.sculptor.databinding.ItemDateBinding

abstract class DateAdapter(itemList: ArrayList<Date>) :
    RecyclerView.Adapter<BoxAdapter.ViewHolder>() {


    interface MyItemClickListener{
        fun onItemCLick(position: Int)
    }
    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener : MyItemClickListener){
        myItemClickListener = itemClickListener
    }



    var DateList: ArrayList<Date> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(itemViewBinding: ItemBoxBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        val layout = itemViewBinding.layoutGoalCard
        val dday = itemViewBinding.tvDDay
    }}

//    override fun onCreateViewHolder()
////        parent: ViewGroup ,
////        viewType: Int
////    ): ViewHolder {
////        return ViewHolder(
////            ItemDateBinding.inflate(
////                LayoutInflater.from(parent.context),
////                parent,
////                false
////            )
////        )
//    }
//
//    override fun getItemCount(): Int = DateList.size
//
//    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
//        holder.dday.text = DateList[position].text
//
//    }
//
//}
