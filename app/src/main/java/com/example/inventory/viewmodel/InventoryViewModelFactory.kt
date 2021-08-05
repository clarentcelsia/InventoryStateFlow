package com.example.inventory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inventory.repository.InventoryRepository

class InventoryViewModelFactory(
    private val repository: InventoryRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InventoryViewModel::class.java)){
            return InventoryViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown viewmodel class")
    }
}


