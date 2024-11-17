package com.example.aidsync.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aidsync.data.entities.DietaryLog
import kotlinx.coroutines.flow.Flow

@Dao
interface DietaryLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(dietaryLog: DietaryLog)

    @Update
    suspend fun updateLog(dietaryLog: DietaryLog)

    @Delete
    suspend fun deleteLog(dietaryLog: DietaryLog)

    @Query("SELECT * FROM dietary_logs ORDER BY date DESC")
    fun getAllLogs(): Flow<List<DietaryLog>>

    @Query("SELECT * FROM dietary_logs WHERE patient_id = :patientId ORDER BY date DESC")
    fun getLogsForPatient(patientId: Int): Flow<List<DietaryLog>>

    @Query("SELECT * FROM dietary_logs WHERE id = :id")
    fun getLogById(id: Int): Flow<DietaryLog>

}