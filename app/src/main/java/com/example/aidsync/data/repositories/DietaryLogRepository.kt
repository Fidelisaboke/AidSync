package com.example.aidsync.data.repositories

import com.example.aidsync.data.dao.DietaryLogDao
import com.example.aidsync.data.entities.DietaryLog
import kotlinx.coroutines.flow.Flow

class DietaryLogRepository(private val dao: DietaryLogDao) {
    val allLogs: Flow<List<DietaryLog>> = dao.getAllLogs()

    suspend fun insertLog(dietaryLog: DietaryLog) {
        dao.insertLog(dietaryLog)
    }

    suspend fun updateLog(dietaryLog: DietaryLog) {
        dao.updateLog(dietaryLog)
    }

    suspend fun deleteLog(dietaryLog: DietaryLog) {
        dao.deleteLog(dietaryLog)
    }

    fun getLogsForPatient(patientId: Int): Flow<List<DietaryLog>> {
        return dao.getLogsForPatient(patientId)
    }

    fun getLogById(id: Int): Flow<DietaryLog> {
        return dao.getLogById(id)
    }

}