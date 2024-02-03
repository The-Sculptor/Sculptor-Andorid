package com.umc.sculptor.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.databinding.StoreItemStatueBinding


class ItemRVAdapter(private val itemList: ArrayList<Item>):RecyclerView.Adapter<ItemRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemCLick(position: Int)
    }
    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener : MyItemClickListener){
        myItemClickListener = itemClickListener
    }




    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemRVAdapter.ViewHolder {
        val binding: StoreItemStatueBinding =
            StoreItemStatueBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemRVAdapter.ViewHolder, position: Int) {

        holder.bind(itemList[position])
        holder.itemView.setOnClickListener{
            myItemClickListener.onItemCLick(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = itemList.size
    inner class ViewHolder(val binding: StoreItemStatueBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item){
            binding.statueNameTv.text = item.name
            binding.itemStatueImg.setImageResource(item.statueImg!!)
            binding.itemStatueBackImg.setImageResource(item.backImg!!)
        }
    }
}