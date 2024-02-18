package com.umc.sculptor.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.umc.sculptor.R
import com.umc.sculptor.data.model.remote.store.WornItems
import com.umc.sculptor.databinding.StoreWearinglistItemBinding

class ItemListRVAdapter(private val itemList: List<WornItems>, private val onCheckChangeListener: (Int, Boolean) -> Unit) : RecyclerView.Adapter<ItemListRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemListRVAdapter.ViewHolder {
        val binding = StoreWearinglistItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, onCheckChangeListener)
    }

    override fun onBindViewHolder(holder: ItemListRVAdapter.ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
    inner class ViewHolder(val binding: StoreWearinglistItemBinding, private val onCheckChangeListener: (Int, Boolean) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WornItems) {
            //binding.wearingListItemImg.setImageResource(item.ItemImg!!)
            binding.wearingListItemGTv.text = "${item.data.stoneItems}g"
            binding.wearingListCheckbtn.setOnClickListener {
                val newPosition = adapterPosition
                //val newSelectedState = !item.isSelected
                //onCheckChangeListener(newPosition, newSelectedState)
            }
//            if (item.isSelected) {
//                binding.wearingListCheckbtn.setImageResource(R.drawable.icon_solid_check)
//            } else {
//                binding.wearingListCheckbtn.setImageResource(R.drawable.icon_outline_check)
//            }
        }
    }
}