package com.example.aidsync.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aidsync.data.entities.CasualtyReport
import kotlinx.coroutines.flow.Flow

@Dao
interface CasualtyReportDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertReport(casualtyReport: CasualtyReport)

    @Update
    suspend fun updateReport(casualtyReport: CasualtyReport)

    @Delete
    suspend fun deleteReport(casualtyReport: CasualtyReport)

    @Query("SELECT * FROM casualty_reports ORDER BY incident_date DESC")
    fun getAllReports(): Flow<List<CasualtyReport>>

    @Query("SELECT * FROM casualty_reports WHERE id = :id")
    fun getReportById(id: Int): Flow<CasualtyReport>

    @Query("SELECT * FROM casualty_reports WHERE casualty_name = :name ORDER BY incident_date DESC")
    fun getReportsByName(name: String): Flow<List<CasualtyReport>>

}