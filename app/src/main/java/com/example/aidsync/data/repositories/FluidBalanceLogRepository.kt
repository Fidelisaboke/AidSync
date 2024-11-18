package com.example.aidsync.data.repositories

import com.example.aidsync.data.dao.FluidBalanceLogDao
import com.example.aidsync.data.entities.FluidBalanceLog
import kotlinx.coroutines.flow.Flow

class FluidBalanceLogRepository(val dao: FluidBalanceLogDao) {
    val allLogs: Flow<List<FluidBalanceLog>> = dao.getAllLogs()

    suspend fun insertLog(log: FluidBalanceLog) {
        dao.insertLog(log)
    }

    suspend fun updateLog(log: FluidBalanceLog) {
        dao.updateLog(log)
    }

    suspend fun deleteLog(log: FluidBalanceLog) {
        dao.deleteLog(log)
    }

    fun getLogsForPatient(patientId: Int): Flow<List<FluidBalanceLog>> {
        return dao.getLogsForPatient(patientId)
    }

    fun getLogById(id: Int): Flow<FluidBalanceLog> {
        return dao.getLogById(id)
    }

}