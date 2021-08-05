package com.example.inventory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Items
import com.example.inventory.repository.InventoryRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class InventoryViewModel(
    private val repository: InventoryRepository,
) : ViewModel() {

    private var _collectItems = MutableStateFlow<List<Items>>(ArrayList())

    fun addNewItem(item: String, price: String, stock: String) {
        if(isValid(item, price, stock)) {
            val items = Items(
                name = item,
                price = price.toDouble(),
                stock = stock.toInt()
            )

            insertItem(items)
        }
    }

    fun collectItems(): StateFlow<List<Items>>{
        selectItem()
        return _collectItems
    }

    fun selectItem() = viewModelScope.launch {
        repository.selectItem().collect {
            _collectItems.value = it
        }
    }

    fun deleteItem(item: Items) = viewModelScope.launch {
        repository.deleteItem(item)
    }

    private fun insertItem(items: Items) = viewModelScope.launch {
        repository.insertItem(items)
    }

    private fun isValid(item: String, price: String, stock: String): Boolean {
        return !(item.isBlank() && price.isBlank() && stock.isBlank())
    }

}