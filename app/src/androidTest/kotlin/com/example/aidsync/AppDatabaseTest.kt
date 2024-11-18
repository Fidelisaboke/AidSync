package com.example.aidsync

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.aidsync.data.AppDatabase
import com.example.aidsync.data.dao.CasualtyReportDao
import com.example.aidsync.data.entities.CasualtyReport
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var db: AppDatabase
    private lateinit var casualtyReportDao: CasualtyReportDao

    /**
     * Set up an in-memory database for testing.
     */
    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        casualtyReportDao = db.casualtyReportDao()
    }

    /**
     * Close the database after each test.
     */
    @After
    @Throws(IOException::class)
    fun tearDown() {
        if (this::db.isInitialized) {
            db.close()
        }
    }

    /**
     * Test that the database is initialized correctly.
     */
    @Test
    fun testDatabaseIsInitialized() {
        assertNotNull(db)
        assertNotNull(casualtyReportDao)
    }

    /**
     * Test that a casualty report can be inserted into the database and retrieved.
     */
    @Test
    fun testInsertAndRetrieveCasualtyReport() = runBlocking {
        val report = CasualtyReport(
            casualtyName = "John Doe",
            casualtyAge = 30,
            contactInfo = "123-456-7890",
            incidentDescription = "Minor injury",
            incidentDate = "2024-01-01",
            incidentTime = "12:00:00",
            incidentLocation = "123 Main St.",
            bodyPartsInjured = "Head",
            stateOfResponsiveness = "Responsive",
            gender = "M"
        )

        casualtyReportDao.insertReport(report)

        val reports = casualtyReportDao.getAllReports().first()

        assertEquals(1, reports.size)
        assertEquals("John Doe", reports[0].casualtyName)
        assertEquals(30, reports[0].casualtyAge)
        assertEquals("123-456-7890", reports[0].contactInfo)
        assertEquals("Minor injury", reports[0].incidentDescription)
        assertEquals("2024-01-01", reports[0].incidentDate)
        assertEquals("12:00:00", reports[0].incidentTime)
        assertEquals("123 Main St.", reports[0].incidentLocation)
        assertEquals("Head", reports[0].bodyPartsInjured)
        assertEquals("Responsive", reports[0].stateOfResponsiveness)
        assertEquals("M", reports[0].gender)

    }
}