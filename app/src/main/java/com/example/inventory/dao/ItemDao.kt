package com.example.inventory.dao

import androidx.room.*
import com.example.inventory.data.Items
import kotlinx.coroutines.flow.Flow

/** This dao allows you to access data in database as an interface */
@Dao
interface ItemDao {

    /**
     * [OnConflictStrategy.Replace] replace the old data with the new data if the data has the same primary key
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertItem(item: Items)

    @Delete
    suspend fun deleteItem(items: Items)

    @Query("SELECT * FROM tbItem")
    fun selectItem(): Flow<List<Items>>

}