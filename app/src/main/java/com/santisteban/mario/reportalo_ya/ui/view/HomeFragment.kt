package com.santisteban.mario.reportalo_ya.ui.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.santisteban.mario.reportalo_ya.data.database.AppDatabase
import com.santisteban.mario.reportalo_ya.data.database.dao.ReporteDao
import com.santisteban.mario.reportalo_ya.data.model.ReporteModel
import com.santisteban.mario.reportalo_ya.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val reportesList = mutableListOf<ReporteModel>()
    private lateinit var reporteDao: ReporteDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appDatabase = Room.databaseBuilder(
            requireContext().applicationContext,
            AppDatabase::class.java, "reportes-db"
        ).build()

        reporteDao = appDatabase.reporteDao()

        binding.btnEnviar.setOnClickListener {
            if (validateForm()) {
                showTermsAndConditionsDialog()
            }
        }
    }

    private fun validateForm(): Boolean {
        val colegio = binding.spnColegio.selectedItem.toString()
        val grado = binding.spnGrado.selectedItem.toString()
        val seccion = binding.spnSeccion.selectedItem.toString()
        val descripcion = binding.edtDescripcion.text.toString()

        if (colegio.isEmpty() || grado.isEmpty() || seccion.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun showTermsAndConditionsDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Términos y Condiciones")
        builder.setMessage("Aceptas los términos y condiciones?")
        builder.setPositiveButton("Aceptar") { _, _ ->
            sendForm()
        }
        builder.setNegativeButton("Cancelar", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun sendForm() {
        val colegio = binding.spnColegio.selectedItem.toString()
        val grado = binding.spnGrado.selectedItem.toString()
        val seccion = binding.spnSeccion.selectedItem.toString()
        val descripcion = binding.edtDescripcion.text.toString()

        if (colegio.isEmpty() || grado.isEmpty() || seccion.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val reporte = ReporteModel(
            id = 0,
            nombreAlumno = "",
            fechaEnvio = "",
            reporteAlumno = "",
            colegio = colegio,
            grado = grado,
            seccion = seccion,
            descripcion = descripcion
        )
        reportesList.add(reporte)

        // Limpiar los campos del formulario después de enviarlo
        binding.spnColegio.setSelection(0)
        binding.spnGrado.setSelection(0)
        binding.spnSeccion.setSelection(0)
        binding.edtDescripcion.text = null

        Toast.makeText(requireContext(), "Formulario enviado", Toast.LENGTH_SHORT).show()

        // Insertar el reporte en la base de datos
        insertReporte(reporte)
    }


    private fun insertReporte(reporte: ReporteModel) {
        // Utiliza el contexto de Dispatchers.IO para ejecutar la operación de inserción en un hilo de fondo
        GlobalScope.launch(Dispatchers.IO) {
            reporteDao.insertReporte(reporte)
        }
    }
}

