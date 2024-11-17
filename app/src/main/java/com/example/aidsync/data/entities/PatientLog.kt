package com.example.aidsync.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient_logs")
data class PatientLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "patient_id")
    val patientId: Int,

    @ColumnInfo(name = "log_type")
    val logType: String,

    val description: String,

    val date: String

    // TODO: Add association with Firebase UID
)