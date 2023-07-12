package com.santisteban.mario.reportalo_ya.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.santisteban.mario.reportalo_ya.data.model.ReporteModel

@Dao
interface ReporteDao {
    @Insert
    suspend fun insertReporte(reporte: ReporteModel)

    @Query("SELECT * FROM reportes")
    suspend fun getAllReportes(): List<ReporteModel>
}


