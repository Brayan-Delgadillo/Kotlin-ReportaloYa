package com.santisteban.mario.reportalo_ya.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.santisteban.mario.reportalo_ya.R
import com.santisteban.mario.reportalo_ya.data.model.ReporteModel

class ReporteAdapter(private val reportesList: MutableList<ReporteModel>) :
    RecyclerView.Adapter<ReporteAdapter.ReporteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_reports, parent, false)
        return ReporteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReporteViewHolder, position: Int) {
        val reporte = reportesList[position]
        holder.bind(reporte)
    }

    override fun getItemCount(): Int {
        return reportesList.size
    }

    fun updateReportesList(reportesList: MutableList<ReporteModel>) {
        this.reportesList.clear()
        this.reportesList.addAll(reportesList)
        notifyDataSetChanged()
    }

    inner class ReporteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val reportTextView: TextView = itemView.findViewById(R.id.reportTextView)

        fun bind(reporte: ReporteModel) {
            nameTextView.text = reporte.nombreAlumno
            dateTextView.text = reporte.fechaEnvio
            reportTextView.text = reporte.reporteAlumno
        }
    }
}
