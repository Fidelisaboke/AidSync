package com.example.aidsync.data.repositories

import com.example.aidsync.data.dao.InventoryItemDao
import com.example.aidsync.data.entities.InventoryItem
import kotlinx.coroutines.flow.Flow

class InventoryItemRepository(val dao: InventoryItemDao) {
    val allItems: Flow<List<InventoryItem>> = dao.getAllItems()

    suspend fun insertItem(item: InventoryItem) {
        dao.insertItem(item)
    }

    suspend fun updateItem(item: InventoryItem) {
        dao.updateItem(item)
    }

    suspend fun deleteItem(item: InventoryItem) {
        dao.deleteItem(item)
    }

    fun getItemById(id: Int): Flow<InventoryItem> {
        return dao.getItemById(id)
    }
}