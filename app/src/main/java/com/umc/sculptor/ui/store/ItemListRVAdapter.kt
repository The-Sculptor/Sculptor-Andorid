package com.umc.sculptor.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.store.Item
import com.umc.sculptor.data.model.remote.store.ItemX
import com.umc.sculptor.databinding.StoreWearinglistItemBinding

class ItemListRVAdapter(itemList: List<ItemX>, private val onCheckChangeListener: (Int, Boolean) -> Unit) : RecyclerView.Adapter<ItemListRVAdapter.ViewHolder>() {

    var itemList: List<ItemX> = itemList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemListRVAdapter.ViewHolder {
        val binding = StoreWearinglistItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, onCheckChangeListener)
    }

    override fun onBindViewHolder(holder: ItemListRVAdapter.ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
    inner class ViewHolder(val binding: StoreWearinglistItemBinding, private val onCheckChangeListener: (Int, Boolean) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemX) {
            //binding.wearingListItemImg.setImageResource(item.ItemImg!!)
            binding.wearingListItemGTv.text = "${item.price}g"
            binding.wearingListCheckbtn.setOnClickListener {
                val newPosition = adapterPosition
                val newSelectedState = !item.isChecked
                onCheckChangeListener(newPosition, newSelectedState)
            }
            if (item.isChecked) {
                binding.wearingListCheckbtn.setImageResource(R.drawable.icon_solid_check)
            } else {
                binding.wearingListCheckbtn.setImageResource(R.drawable.icon_outline_check)
            }
        }
    }
}