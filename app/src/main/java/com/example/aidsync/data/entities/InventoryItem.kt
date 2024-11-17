package com.example.aidsync.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory_items")
data class InventoryItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "item_name")
    val itemName: String,

    val description: String,

    val category: String,

    val quantity: Int,

    @ColumnInfo(name = "expiration_date")
    val expirationDate: String,
)