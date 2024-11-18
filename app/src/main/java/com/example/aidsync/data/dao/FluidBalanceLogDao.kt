package com.example.aidsync.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aidsync.data.entities.FluidBalanceLog
import kotlinx.coroutines.flow.Flow

@Dao
interface FluidBalanceLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(fluidBalanceLog: FluidBalanceLog)

    @Update
    suspend fun updateLog(fluidBalanceLog: FluidBalanceLog)

    @Delete
    suspend fun deleteLog(fluidBalanceLog: FluidBalanceLog)

    @Query("SELECT * FROM fluid_balance_logs ORDER BY date DESC")
    fun getAllLogs(): Flow<List<FluidBalanceLog>>

    @Query("SELECT * FROM fluid_balance_logs WHERE patient_id = :patientId ORDER BY date DESC")
    fun getLogsForPatient(patientId: Int): Flow<List<FluidBalanceLog>>

    @Query("SELECT * FROM fluid_balance_logs WHERE id = :id")
    fun getLogById(id: Int): Flow<FluidBalanceLog>

}