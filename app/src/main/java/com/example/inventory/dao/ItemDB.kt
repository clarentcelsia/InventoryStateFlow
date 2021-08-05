package com.example.inventory.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventory.data.Items

/**
 *  @param entities, as the only class in database
 *
 *  @param version, whenever you change the schema of the database table, you'll have to increase the version number
 *
 *  @param exportSchema, not to keep schema version history backups
 */
@Database(entities = [Items::class], version = 1, exportSchema = false)
abstract class ItemDB: RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object{
        /**
         *  This instance value will be written and read from the main memory, never be cached.
         *  This helps to make sure the value is always up-to-date and visible for all other threads.
         */
        @Volatile
        private var INSTANCE: ItemDB? = null
        private val LOCK = Any()

       fun createDatabase (context: Context) = INSTANCE?: synchronized(LOCK){
            INSTANCE?: databaseBuilder(context).also { INSTANCE = it }
        }

        /**
         * [addMigrations] Provides a migration object for the schema changes,
         * This converts the data to the new schema
         *
         * [fallbackToDestructiveMigration] Database will be destroyed and rebuilt when the schema changes
         * This clears the data
         */
        private fun databaseBuilder(context: Context) =
            Room.databaseBuilder(
                context,
                ItemDB::class.java,
                "dbInventory"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}