package com.example.inventory

import android.app.Application
import com.example.inventory.container.Container
import com.example.inventory.dao.ItemDB

class MyApp: Application() {

    /**
     *  These objects will be created when they're first needed rather than at app startup.
     */
    val database : ItemDB by lazy { ItemDB.createDatabase(this) }

    val container : Container by lazy { Container(database) }
}