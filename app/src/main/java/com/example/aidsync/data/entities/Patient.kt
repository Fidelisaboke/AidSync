package com.example.aidsync.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class Patient(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val gender: String,

    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: String,

    @ColumnInfo(name = "contact_info")
    val contactInfo: String,

    val address: String,
)