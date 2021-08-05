package com.example.inventory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.data.Items
import com.example.inventory.databinding.ItemInventoryBinding

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding: ItemInventoryBinding = ItemInventoryBinding.bind(view)
    }

    private val itemDifferCallback = object : DiffUtil.ItemCallback<Items>(){
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }

    }

    val itemAsyncDiffer = AsyncListDiffer(this, itemDifferCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemInventoryBinding = ItemInventoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemInventoryBinding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemIDX = itemAsyncDiffer.currentList[position]
        with(holder){
            binding.itemName.text = itemIDX.name
            binding.itemPrice.text = itemIDX.price.toString()
            binding.itemStock.text = itemIDX.stock.toString()
        }
    }

    override fun getItemCount(): Int = itemAsyncDiffer.currentList.size
}