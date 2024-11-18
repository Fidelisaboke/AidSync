package com.example.aidsync.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fluid_balance_logs")
data class FluidBalanceLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "patient_id")
    val patientId: Int,

    @ColumnInfo(name = "intake_volume")
    val intakeVolume: Double,

    @ColumnInfo(name = "output_volume")
    val outputVolume: Double,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "recorded_by")
    val recordedBy: Int

    // TODO: Add association with Firebase UID
)