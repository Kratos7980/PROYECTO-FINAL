package com.example.greensnap.vista

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.greensnap.databinding.ActivityPantallaPreferencesBinding

class PantallaPreferences : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaPreferencesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val notificacionesHabilitadas = sharedPreferences.getBoolean("pref_checkbox", false)
        val nombreUsuario = sharedPreferences.getString("pref_usuario", "usuario4")
        Log.e("Carlos2", "nombreUsuario: $nombreUsuario")

    }
}