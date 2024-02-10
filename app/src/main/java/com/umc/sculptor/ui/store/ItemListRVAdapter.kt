package com.umc.sculptor.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.databinding.StoreWearinglistItemBinding

class ItemListRVAdapter(private val itemList: List<Item_WB>) : RecyclerView.Adapter<ItemListRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemListRVAdapter.ViewHolder {
        val binding = StoreWearinglistItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListRVAdapter.ViewHolder, position: Int) {

        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
    inner class ViewHolder(val binding: StoreWearinglistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item_WB) {
            binding.wearingListItemImg.setImageResource(item.ItemImg!!)
            binding.wearingListItemGTv.text = "${item.price}g"
        }
    }








}