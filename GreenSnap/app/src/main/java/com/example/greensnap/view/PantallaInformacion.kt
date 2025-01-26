package com.example.greensnap.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.greensnap.databinding.ActivityPantallaInformacionBinding
import com.example.greensnap.dbconexion.JardinHelper
import com.example.greensnap.model.Planta

class PantallaInformacion : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaInformacionBinding

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra("datos")
        val planta = bundle?.getSerializable("planta", Planta::class.java) as Planta

        binding.imgPlanta.setImageResource(resources.getIdentifier(planta.imagen, "drawable", this.packageName))
        binding.titleInfo.text = planta.nombre_planta

        binding.btnAdd.setOnClickListener {
            AlertDialog(planta)
        }

    }

    private fun AlertDialog(planta: Planta) {
        AlertDialog.Builder(this)
            .setTitle("Confirmación")
            .setMessage("¿Estás seguro de que deseas añadir esta planta?")
            .setPositiveButton("SI",) { dialog, which ->
                JardinHelper.addPlanta(planta, this)
                intent = Intent(this, PantallaPrincipal::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("NO", ){ dialog, which ->
                Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
                intent = Intent(this, PantallaPrincipal::class.java)
                startActivity(intent)
            }
            .show()
    }

}
