package com.santisteban.mario.reportalo_ya.ui.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.santisteban.mario.reportalo_ya.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

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
            // Aquí puedes implementar la lógica para enviar el formulario
            sendForm()
        }
        builder.setNegativeButton("Cancelar", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun sendForm() {
        // Aquí puedes implementar la lógica para enviar el formulario
        // y realizar cualquier otra acción necesaria
    }
}
