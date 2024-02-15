package com.umc.sculptor.ui.museum

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.data.model.remote.museum.Stone
import com.umc.sculptor.databinding.ItemMuseumProfileBinding

class MuseumSculptorAdapter(itemList: List<Stone>):
    RecyclerView.Adapter<MuseumSculptorAdapter.ViewHolder>() {

    var sculptorList: List<Stone> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    interface MyItemClickListener{
        fun onItemClick(position: Int)

    }
    private var myItemClickListener: MyItemClickListener?=null
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        this.myItemClickListener=itemClickListener
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemMuseumProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    inner class ViewHolder(val binding: ItemMuseumProfileBinding)
        : RecyclerView.ViewHolder(binding.root){
        val sculptorDay=binding.museumSculptorDay
        val sculptorTitle=binding.museumSculptorTitle
        val sculptorDate=binding.museumSculptorDate
        val sculptorImage=binding.museumSculptorImg
    }
    override fun onBindViewHolder(holder: MuseumSculptorAdapter.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.sculptorDay.text = sculptorList[position].dDay
        holder.sculptorTitle.text = sculptorList[position].name
        holder.sculptorDate.text = sculptorList[position].startDate
        //holder.sculptorImage.setImageResource(sculptorList[position].id!!)
        holder.itemView.setOnClickListener{
            myItemClickListener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int =sculptorList.size



}