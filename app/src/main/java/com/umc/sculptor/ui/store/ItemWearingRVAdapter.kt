package com.umc.sculptor.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.databinding.StoreItemWearingBinding

class ItemWearingRVAdapter (private val itemList: ArrayList<Item_WB>): RecyclerView.Adapter<ItemWearingRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemCLick(position: Int)
    }

    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener : MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemWearingRVAdapter.ViewHolder {
        val binding: StoreItemWearingBinding =
            StoreItemWearingBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemWearingRVAdapter.ViewHolder, position: Int) {

        holder.bind(itemList[position])
        holder.itemView.setOnClickListener{
            myItemClickListener.onItemCLick(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = itemList.size
    inner class ViewHolder(val binding: StoreItemWearingBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item_WB){
            binding.itemUsingbgImg.setImageResource(item.backImg!!)
            binding.howmuchTv.setText("${item.price!!}")
            binding.itemUsingitemImg.setImageResource(item.ItemImg!!)
        }
    }
}