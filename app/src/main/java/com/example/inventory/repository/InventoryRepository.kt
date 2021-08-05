package com.example.inventory.repository

import com.example.inventory.dao.ItemDB
import com.example.inventory.data.Items
import kotlinx.coroutines.flow.Flow

class InventoryRepository(private val database: ItemDB) {

    fun selectItem(): Flow<List<Items>> = database.itemDao().selectItem()

    suspend fun insertItem(items: Items){
        database.itemDao().upsertItem(items)
    }

    suspend fun deleteItem(items: Items){
        database.itemDao().deleteItem(items)
    }

}