package com.example.aidsync.data

import androidx.room.Database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aidsync.data.dao.CasualtyReportDao
import com.example.aidsync.data.entities.CasualtyReport


@Database(entities = [CasualtyReport::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun casualtyReportDao(): CasualtyReportDao

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