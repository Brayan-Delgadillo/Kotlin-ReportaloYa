package com.santisteban.mario.reportalo_ya.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santisteban.mario.reportalo_ya.data.database.dao.ReporteDao
import com.santisteban.mario.reportalo_ya.data.model.ReporteModel

@Database(entities = [ReporteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reporteDao(): ReporteDao
}
