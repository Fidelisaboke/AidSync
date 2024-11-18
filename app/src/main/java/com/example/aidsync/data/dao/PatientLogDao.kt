package com.example.aidsync.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aidsync.data.entities.PatientLog
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: PatientLog)

    @Update
    suspend fun updateLog(log: PatientLog)

    @Delete
    suspend fun deleteLog(log: PatientLog)

    @Query("SELECT * FROM patient_logs")
    fun getAllLogs(): Flow<List<PatientLog>>

    @Query("SELECT * FROM patient_logs WHERE id = :id")
    fun getLogById(id: Int): Flow<PatientLog>

    @Query("SELECT * FROM patient_logs WHERE patient_id = :patientId")
    fun getLogsForPatient(patientId: Int): Flow<List<PatientLog>>

}