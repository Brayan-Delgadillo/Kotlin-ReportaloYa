package com.santisteban.mario.reportalo_ya.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.santisteban.mario.reportalo_ya.User
import com.santisteban.mario.reportalo_ya.databinding.FragmentUserBinding
import com.santisteban.mario.reportalo_ya.util.SharedPreferenceUtil

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        sharedPreferenceUtil = SharedPreferenceUtil().apply {
            setSharedPreference(requireContext())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user: User? = sharedPreferenceUtil.getUser()
        user?.let {
            binding.tvName.text = it.name
            binding.tvEmail.text = it.email
        }
        binding.btnDeleteAccount.setOnClickListener {
            showDeleteConfirmationDialog()
        }
    }

    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmación")
        builder.setMessage("¿Estás seguro de eliminar tu cuenta?")
        builder.setPositiveButton("Eliminar") { _, _ ->
            // Aquí puedes implementar la lógica para eliminar la cuenta del usuario
            // y realizar cualquier otra acción necesaria
            deleteAccount()
        }
        builder.setNegativeButton("Cancelar", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun deleteAccount() {
        // Obtén el usuario actual desde SharedPreferenceUtil
        val user: User? = sharedPreferenceUtil.getUser()

        // Elimina el usuario actual o realiza cualquier otra acción necesaria
        user?.let {
            sharedPreferenceUtil.clearUser()

            // Aquí puedes realizar acciones adicionales, como borrar los datos del usuario de la base de datos, etc.
        }

        // Redirige al LoginActivity o realiza otras acciones necesarias
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}

