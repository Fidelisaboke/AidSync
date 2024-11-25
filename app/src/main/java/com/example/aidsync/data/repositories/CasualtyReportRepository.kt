package com.example.aidsync.data.repositories

import com.example.aidsync.data.dao.CasualtyReportDao
import com.example.aidsync.data.entities.CasualtyReport
import kotlinx.coroutines.flow.Flow

class CasualtyReportRepository(private val dao: CasualtyReportDao) {
    val allReports: Flow<List<CasualtyReport>> = dao.getAllReports()

    suspend fun insertCasualtyReport(casualtyReport: CasualtyReport) {
        dao.insertReport(casualtyReport)
    }

    suspend fun updateCasualtyReport(casualtyReport: CasualtyReport) {
        dao.updateReport(casualtyReport)
    }

    suspend fun deleteCasualtyReport(casualtyReport: CasualtyReport) {
        dao.deleteReport(casualtyReport)
    }

    fun getCasualtyReportById(id: Int): Flow<CasualtyReport?> {
        return dao.getReportById(id)
    }
}
