package com.umc.sculptor.ui.store

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.home.Data
import com.umc.sculptor.data.model.remote.store.Stone
import com.umc.sculptor.databinding.StoreItemStatueBinding


class ItemRVAdapter(itemList: List<Stone>):RecyclerView.Adapter<ItemRVAdapter.ViewHolder>() {

    var itemList: List<Stone> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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