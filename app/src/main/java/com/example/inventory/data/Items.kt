package com.example.inventory.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbItem")
data class Items(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "stock")
    val stock: Int

    /** Annotates with [@ColumnInfo] to customize the name of each column rather than variable name */
)