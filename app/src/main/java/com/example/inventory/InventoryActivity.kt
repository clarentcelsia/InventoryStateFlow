package com.example.inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inventory.adapter.ItemAdapter
import com.example.inventory.databinding.ActivityInventoryBinding
import com.example.inventory.viewmodel.InventoryViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class InventoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInventoryBinding

    private lateinit var myModels: InventoryViewModel

    private lateinit var itemAdapter: ItemAdapter

    private val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
    ){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            TODO("Not yet implemented")
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val item_selected = itemAdapter.itemAsyncDiffer.currentList[viewHolder.adapterPosition]
            myModels.deleteItem(item_selected)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInventoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appContainer = (application as MyApp).container.factory

        myModels = ViewModelProvider(this, appContainer).get(InventoryViewModel::class.java)

        itemAdapter = ItemAdapter()
        binding.items.apply {
            layoutManager = LinearLayoutManager(this@InventoryActivity)
            setHasFixedSize(true)
            adapter = itemAdapter
        }

        fetchInventories()

        ItemTouchHelper(itemTouchHelper).apply { attachToRecyclerView(binding.items) }

    }

    private fun fetchInventories(){
        lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myModels.collectItems().collect {
                    itemAdapter.itemAsyncDiffer.submitList(it)
                }
            }
        }

    }
}