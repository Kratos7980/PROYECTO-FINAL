package com.example.greensnap.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greensnap.R
import com.example.greensnap.databinding.ActivityPantallaPlantasBinding
import com.example.greensnap.databinding.ActivityPantallaPrincipalBinding

class PantallaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}