package com.example.aidsync.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aidsync.data.entities.InventoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: InventoryItem)

    @Update
    suspend fun updateItem(item: InventoryItem)

    @Delete
    suspend fun deleteItem(item: InventoryItem)

    @Query("SELECT * FROM inventory_items")
    fun getAllItems(): Flow<List<InventoryItem>>

    @Query("SELECT * FROM inventory_items WHERE id = :id")
    fun getItemById(id: Int): Flow<InventoryItem>

}