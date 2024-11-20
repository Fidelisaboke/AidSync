package com.example.aidsync.data

import androidx.room.Database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aidsync.data.dao.CasualtyReportDao
import com.example.aidsync.data.dao.DietaryLogDao
import com.example.aidsync.data.dao.FluidBalanceLogDao
import com.example.aidsync.data.dao.InventoryItemDao
import com.example.aidsync.data.dao.PatientDao
import com.example.aidsync.data.dao.PatientLogDao
import com.example.aidsync.data.entities.CasualtyReport
import com.example.aidsync.data.entities.DietaryLog
import com.example.aidsync.data.entities.FluidBalanceLog
import com.example.aidsync.data.entities.InventoryItem
import com.example.aidsync.data.entities.Patient
import com.example.aidsync.data.entities.PatientLog


@Database(
    entities = [
        CasualtyReport::class,
        DietaryLog::class,
        FluidBalanceLog::class,
        InventoryItem::class,
        Patient::class,
        PatientLog::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun casualtyReportDao(): CasualtyReportDao
    abstract fun dietaryLogDao(): DietaryLogDao
    abstract fun fluidBalanceLogDao(): FluidBalanceLogDao
    abstract fun inventoryItemDao(): InventoryItemDao
    abstract fun patientDao(): PatientDao
    abstract fun patientLogDao(): PatientLogDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration().build().also {
                        instance = it
                    }

            }
        }
    }

}