package com.example.aidsync.data

import com.example.aidsync.data.entities.CasualtyReport
import com.example.aidsync.data.entities.DietaryLog
import com.example.aidsync.data.entities.FluidBalanceLog
import com.example.aidsync.data.entities.InventoryItem
import com.example.aidsync.data.entities.Patient
import com.example.aidsync.data.entities.PatientLog

object TestData {

    /**
     * Sample casualty reports.
     */
    val casualtyReports = listOf(
        CasualtyReport(
            recordedBy = 101,
            casualtyName = "John Doe",
            casualtyAge = 29,
            contactInfo = "123-456-7890",
            incidentDescription = "Slipped and fell on a wet floor",
            incidentLocation = "Downtown Shopping Mall",
            incidentDate = "2024-11-19",
            incidentTime = "14:30",
            bodyPartsInjured = "Left Arm, Right Knee",
            stateOfResponsiveness = "Conscious",
            gender = "Male"
        ),
        CasualtyReport(
            recordedBy = 102,
            casualtyName = "Jane Smith",
            casualtyAge = 34,
            contactInfo = "987-654-3210",
            incidentDescription = "Hit by a car while crossing the street",
            incidentLocation = "5th Avenue, New York",
            incidentDate = "2024-11-18",
            incidentTime = "18:15",
            bodyPartsInjured = "Head, Right Leg",
            stateOfResponsiveness = "Semi-conscious",
            gender = "Female"
        ),
        CasualtyReport(
            recordedBy = 101,
            casualtyName = "Alice Johnson",
            casualtyAge = 22,
            contactInfo = null, // Unknown or unavailable contact information
            incidentDescription = "Burn from kitchen fire",
            incidentLocation = "42 Elm Street, Apartment 5B",
            incidentDate = "2024-11-17",
            incidentTime = "20:45",
            bodyPartsInjured = "Left Hand",
            stateOfResponsiveness = "Conscious",
            gender = "Female"
        )
    )

    /**
     * Sample dietary logs.
     */
    val dietaryLogs = listOf(
        DietaryLog(
            patientId = 1,
            dietDescription = "Breakfast: Oatmeal with fruits",
            date = "2024-11-19",
            time = "08:00",
            recordedBy = 201
        ),
        DietaryLog(
            patientId = 2,
            dietDescription = "Lunch: Grilled chicken salad",
            date = "2024-11-19",
            time = "12:30",
            recordedBy = 202
        ),
        DietaryLog(
            patientId = 1,
            dietDescription = "Snack: Greek yogurt with honey",
            date = "2024-11-19",
            time = "15:00",
            recordedBy = 201
        ),
        DietaryLog(
            patientId = 3,
            dietDescription = "Dinner: Baked salmon with vegetables",
            date = "2024-11-18",
            time = "19:00",
            recordedBy = 203
        ),
        DietaryLog(
            patientId = 4,
            dietDescription = "Breakfast: Scrambled eggs and toast",
            date = "2024-11-17",
            time = "07:30",
            recordedBy = 204
        ),
    )

    /**
     * Sample fluid balance logs.
     */
    val fluidBalanceLogs = listOf(
        FluidBalanceLog(
            patientId = 1,
            intakeVolume = 1500.0,
            outputVolume = 1200.0,
            date = "2024-11-19",
            time = "08:00",
            recordedBy = 201
        ),
        FluidBalanceLog(
            patientId = 2,
            intakeVolume = 1800.0,
            outputVolume = 1700.0,
            date = "2024-11-19",
            time = "12:30",
            recordedBy = 202
        ),
        FluidBalanceLog(
            patientId = 1,
            intakeVolume = 500.0,
            outputVolume = 400.0,
            date = "2024-11-19",
            time = "15:00",
            recordedBy = 201
        ),
        FluidBalanceLog(
            patientId = 3,
            intakeVolume = 2000.0,
            outputVolume = 1950.0,
            date = "2024-11-18",
            time = "19:00",
            recordedBy = 203
        ),
        FluidBalanceLog(
            patientId = 4,
            intakeVolume = 1600.0,
            outputVolume = 1400.0,
            date = "2024-11-17",
            time = "07:30",
            recordedBy = 204
        ),
    )

    /**
     * Sample inventory items.
     */
    val inventoryItems = listOf(
        InventoryItem(
            itemName = "Paracetamol",
            description = "Used to treat pain and fever.",
            category = "Medication",
            quantity = 100,
            expirationDate = "2025-05-01"
        ),
        InventoryItem(
            itemName = "Bandages",
            description = "Used for wound dressing and protection.",
            category = "Medical Supplies",
            quantity = 50,
            expirationDate = "2026-10-10"
        ),
        InventoryItem(
            itemName = "Syringes",
            description = "Used for administering injections.",
            category = "Medical Supplies",
            quantity = 200,
            expirationDate = "2027-01-15"
        ),
        InventoryItem(
            itemName = "Insulin",
            description = "Used to manage diabetes.",
            category = "Medication",
            quantity = 80,
            expirationDate = "2025-11-20"
        ),
        InventoryItem(
            itemName = "Stethoscope",
            description = "Used for medical examinations.",
            category = "Equipment",
            quantity = 10,
            expirationDate = "2028-12-31"
        ),
        InventoryItem(
            itemName = "Face Masks",
            description = "Used for respiratory protection.",
            category = "Protective Gear",
            quantity = 300,
            expirationDate = "2025-03-15"
        ),
        InventoryItem(
            itemName = "Gauze Pads",
            description = "Used for wound care and protection.",
            category = "Medical Supplies",
            quantity = 150,
            expirationDate = "2026-06-30"
        ),
        InventoryItem(
            itemName = "Thermometer",
            description = "Used to measure body temperature.",
            category = "Equipment",
            quantity = 25,
            expirationDate = "2027-09-12"
        )
    )

    /**
     * Sample patients.
     */
    val patients = listOf(
        Patient(
            name = "John Doe",
            gender = "Male",
            dateOfBirth = "1985-04-23",
            contactInfo = "john.doe@example.com, 123-456-7890",
            address = "123 Maple Street, Springfield"
        ),
        Patient(
            name = "Jane Smith",
            gender = "Female",
            dateOfBirth = "1990-06-15",
            contactInfo = "jane.smith@example.com, 987-654-3210",
            address = "456 Oak Avenue, Springfield"
        ),
        Patient(
            name = "Alice Johnson",
            gender = "Female",
            dateOfBirth = "1975-08-09",
            contactInfo = "alice.johnson@example.com, 555-123-4567",
            address = "789 Pine Lane, Springfield"
        ),
        Patient(
            name = "Robert Brown",
            gender = "Male",
            dateOfBirth = "1982-11-20",
            contactInfo = "robert.brown@example.com, 444-789-1234",
            address = "321 Cedar Road, Springfield"
        ),
        Patient(
            name = "Emily Davis",
            gender = "Female",
            dateOfBirth = "1995-12-30",
            contactInfo = "emily.davis@example.com, 333-654-9870",
            address = "654 Birch Boulevard, Springfield"
        )
    )

    /**
     * Sample patient logs.
     */
    val patientLogs = listOf(
        PatientLog(
            patientId = 1,
            logType = "Medical Visit",
            description = "Patient visited for a routine check-up.",
            date = "2024-11-19"
        ),
        PatientLog(
            patientId = 2,
            logType = "Medication",
            description = "Prescribed antibiotics for infection.",
            date = "2024-11-18"
        ),
        PatientLog(
            patientId = 1,
            logType = "Lab Test",
            description = "Blood test performed to check cholesterol levels.",
            date = "2024-11-17"
        ),
        PatientLog(
            patientId = 3,
            logType = "Follow-Up",
            description = "Follow-up appointment for previous surgery.",
            date = "2024-11-16"
        ),
        PatientLog(
            patientId = 4,
            logType = "Emergency",
            description = "Patient admitted to ER for severe abdominal pain.",
            date = "2024-11-15"
        ),
        PatientLog(
            patientId = 2,
            logType = "Consultation",
            description = "Consulted with dietitian for weight management.",
            date = "2024-11-14"
        ),
        PatientLog(
            patientId = 3,
            logType = "Vaccination",
            description = "Administered flu vaccine.",
            date = "2024-11-13"
        ),
        PatientLog(
            patientId = 4,
            logType = "Discharge",
            description = "Patient discharged after overnight observation.",
            date = "2024-11-12"
        )
    )




}