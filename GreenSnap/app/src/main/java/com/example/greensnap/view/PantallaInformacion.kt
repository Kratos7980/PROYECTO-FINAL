package com.example.greensnap.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greensnap.R
import com.example.greensnap.databinding.ActivityPantallaInformacionBinding

class PantallaInformacion : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaInformacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}