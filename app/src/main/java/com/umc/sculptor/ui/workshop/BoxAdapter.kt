package com.umc.sculptor.ui.workshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.umc.sculptor.R
import com.umc.sculptor.databinding.ItemBoxBinding


class BoxAdapter(itemList: ArrayList<Box>) :
    RecyclerView.Adapter<BoxAdapter.ViewHolder>() {

    private var boxlist: List<Box> = emptyList()

    interface MyItemClickListener{
        fun onItemCLick(position: Int)
    }
    private lateinit var myItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    fun updateData(newBoxList: List<Box>){
        boxlist = newBoxList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemViewBinding: ItemBoxBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
            val layout = itemViewBinding.layoutGoalCard
            val dday = itemViewBinding.tvDDay


    init {
        itemViewBinding.root.setOnClickListener {
            adapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let {
                myItemClickListener.onItemCLick(it)
            }
        }
    }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemBoxBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = boxlist.size

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.dday.text = boxlist[position].text

    }

}
