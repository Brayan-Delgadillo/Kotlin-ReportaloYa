package com.santisteban.mario.reportalo_ya.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.santisteban.mario.reportalo_ya.R
import com.santisteban.mario.reportalo_ya.adapter.ReporteAdapter
import com.santisteban.mario.reportalo_ya.data.database.AppDatabase
import com.santisteban.mario.reportalo_ya.data.database.dao.ReporteDao
import com.santisteban.mario.reportalo_ya.data.model.ReporteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ReportsFragment : Fragment(R.layout.fragment_reports) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReporteAdapter
    private val reportesList = mutableListOf<ReporteModel>()
    private lateinit var reporteDao: ReporteDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ReporteAdapter(reportesList)
        recyclerView.adapter = adapter

        // Obtener instancia de la base de datos y el DAO
        val appDatabase = Room.databaseBuilder(
            requireContext().applicationContext,
            AppDatabase::class.java, "reportes-db"
        ).build()
        reporteDao = appDatabase.reporteDao()

        displayReportes()
    }

    private fun displayReportes() {
        // Obtener los reportes de la base de datos
        GlobalScope.launch(Dispatchers.IO) {
            val reportes = reporteDao.getAllReportes()

            // Actualizar la lista de reportes en el hilo principal
            launch(Dispatchers.Main) {
                reportesList.clear()
                reportesList.addAll(reportes)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun updateReportesList(reportesList: MutableList<ReporteModel>) {
        this.reportesList.clear()
        this.reportesList.addAll(reportesList)
        adapter.notifyDataSetChanged()
    }
}

