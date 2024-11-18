package com.example.aidsync.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "casualty_reports")
data class CasualtyReport(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "casualty_name")
    val casualtyName: String,

    @ColumnInfo(name = "casualty_age")
    val casualtyAge: Int,

    @ColumnInfo(name = "contact_info")
    val contactInfo: String?,

    @ColumnInfo(name = "incident_description")
    val incidentDescription: String,

    @ColumnInfo(name = "incident_location")
    val incidentLocation: String,

    @ColumnInfo(name = "incident_date")
    val incidentDate: String,

    @ColumnInfo(name = "incident_time")
    val incidentTime: String,

    @ColumnInfo(name = "body_parts_injured")
    val bodyPartsInjured: String?,

    @ColumnInfo(name = "state_of_responsiveness")
    val stateOfResponsiveness: String?,

    val gender: String
)