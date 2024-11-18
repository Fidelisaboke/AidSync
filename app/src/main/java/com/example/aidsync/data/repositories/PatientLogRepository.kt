package com.example.aidsync.data.repositories

import com.example.aidsync.data.dao.PatientLogDao
import com.example.aidsync.data.entities.PatientLog
import kotlinx.coroutines.flow.Flow

class PatientLogRepository(val dao: PatientLogDao) {
    val allLogs: Flow<List<PatientLog>> = dao.getAllLogs()

    suspend fun insertLog(log: PatientLog) {
        dao.insertLog(log)
    }

    suspend fun updateLog(log: PatientLog) {
        dao.updateLog(log)
    }

    suspend fun deleteLog(log: PatientLog) {
        dao.deleteLog(log)
    }

    fun getLogById(id: Int): Flow<PatientLog> {
        return dao.getLogById(id)
    }

    fun getLogsForPatient(patientId: Int): Flow<List<PatientLog>> {
        return dao.getLogsForPatient(patientId)
    }
}