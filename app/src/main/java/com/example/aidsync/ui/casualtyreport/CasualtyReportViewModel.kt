package com.example.aidsync.ui.casualtyreport

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.aidsync.data.AppDatabase
import com.example.aidsync.data.entities.CasualtyReport
import com.example.aidsync.data.repositories.CasualtyReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CasualtyReportViewModel(application: Application): AndroidViewModel(application) {
    private val repository: CasualtyReportRepository
    val allReports: Flow<List<CasualtyReport>>

    init {
        val dao = AppDatabase.getDatabase(application).casualtyReportDao()
        repository = CasualtyReportRepository(dao)
        allReports = repository.allReports
    }

    fun insertReport(report: CasualtyReport) = viewModelScope.launch{
        repository.insertCasualtyReport(report)
    }
}