package com.santisteban.mario.reportalo_ya.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reportes")
data class ReporteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombreAlumno: String,
    val fechaEnvio: String,
    val reporteAlumno: String,
    val colegio: String,
    val grado: String,
    val seccion: String,
    val descripcion: String
)

