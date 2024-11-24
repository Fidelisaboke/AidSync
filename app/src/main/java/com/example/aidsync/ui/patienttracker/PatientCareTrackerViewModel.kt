package com.example.aidsync.ui.patienttracker

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// Data class to hold details about patient sections (Vital Signs, Medications, etc.)
data class PatientDetail(
    val title: String,
    val description: String
)

class PatientCareTrackerViewModel : ViewModel() {

    // Holds the patient details for each section
    private val _patientDetails = mutableStateListOf<PatientDetail>()
    val patientDetails: List<PatientDetail> get() = _patientDetails

    init {
        // Initialize with sample data
        loadSampleData()
    }

    private fun loadSampleData() {
        _patientDetails.add(PatientDetail("Heart Rate", "75 bpm"))
        _patientDetails.add(PatientDetail("Temperature", "98.6°F"))
        _patientDetails.add(PatientDetail("Aspirin 100mg", "Medication"))
        _patientDetails.add(PatientDetail("Vitamin D 200 IU", "Medication"))
    }

    fun addPatientDetail(detail: PatientDetail) {
        _patientDetails.add(detail)
    }
}

