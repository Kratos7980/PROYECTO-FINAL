package com.example.greensnap.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.greensnap.databinding.ActivityPantallaInformacionBinding
import com.example.greensnap.model.Planta
import com.example.greensnap.viewModel.CategoriasViewModel

class PantallaInformacion : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaInformacionBinding
    private val viewModelCategorias:CategoriasViewModel by viewModels()
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra("datos")
        val planta = bundle?.getSerializable("planta", Planta::class.java) as Planta

        binding.imgPlanta.setImageResource(resources.getIdentifier(planta.imagen, "drawable", this.packageName))
        binding.titleInfo.text = planta.nombre_planta
        binding.txtDesc.text = planta.descripcion

        binding.btnGuiaUsuario.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("planta", planta)
            bundle.putString("pantalla","guia")
            val intent = Intent(this, PantallaCuidados::class.java)
            intent.putExtra("datos",bundle)
            startActivity(intent)
        }

    }

}
