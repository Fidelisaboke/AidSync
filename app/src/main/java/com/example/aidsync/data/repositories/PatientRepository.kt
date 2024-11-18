package com.example.aidsync.data.repositories

import com.example.aidsync.data.dao.PatientDao
import com.example.aidsync.data.entities.Patient
import kotlinx.coroutines.flow.Flow

class PatientRepository(val dao: PatientDao) {
    val allPatients: Flow<List<Patient>> = dao.getAllPatients()

    suspend fun insertPatient(patient: Patient) {
        dao.insertPatient(patient)
    }

    suspend fun updatePatient(patient: Patient) {
        dao.updatePatient(patient)
    }

    suspend fun deletePatient(patient: Patient) {
        dao.deletePatient(patient)
    }

    fun getPatientById(id: Int): Flow<Patient> {
        return dao.getPatientById(id)
    }
}