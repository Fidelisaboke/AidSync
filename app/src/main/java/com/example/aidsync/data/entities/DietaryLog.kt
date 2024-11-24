package com.example.aidsync.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dietary_logs")
data class DietaryLog (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "patient_id")
    val patientId: Int,

    @ColumnInfo(name = "diet_description")
    val dietDescription: String,

    val date: String,

    val time: String,

    @ColumnInfo(name = "recorded_by")
    val recordedBy: Int
)