package com.example.inventory.container

import com.example.inventory.dao.ItemDB
import com.example.inventory.repository.InventoryRepository
import com.example.inventory.viewmodel.InventoryViewModelFactory

class Container(database: ItemDB) {

    val myRepository = InventoryRepository(database)

    val factory = InventoryViewModelFactory(myRepository)
}