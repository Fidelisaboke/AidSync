package com.example.aidsync.ui.patienttracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.aidsync.data.AppDatabase
import com.example.aidsync.data.TestData
import com.example.aidsync.data.entities.PatientLog
import com.example.aidsync.data.repositories.PatientLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class PatientCareTrackerViewModel(application: Application) : AndroidViewModel(application) {
    private val patientLogRepository: PatientLogRepository
    val allLogs: Flow<List<PatientLog>>

    init {
        val patientLogDao = AppDatabase.getDatabase(application).patientLogDao()
        patientLogRepository = PatientLogRepository(patientLogDao)
        allLogs = patientLogRepository.allLogs

        // Initialize with data already entered in test data
        viewModelScope.launch {
            TestData.patientLogs.forEach{
                patientLogRepository.insertLog(it)
            }
        }
    }

    fun addPatientLog(patientLog: PatientLog) = viewModelScope.launch {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = currentDate.format(formatter)

        val currentTime = LocalTime.now()
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formattedTime = currentTime.format(timeFormatter)

        val newPatientLog = patientLog.copy(date = formattedDate, time = formattedTime)
        patientLogRepository.insertLog(newPatientLog)
    }
}

