package com.example.greensnap.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.greensnap.R
import com.example.greensnap.databinding.ActivityPantallaPreferencesBinding
import com.google.android.material.appbar.MaterialToolbar

class PantallaPreferences : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaPreferencesBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        // Instancio el preferenceManager
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        // Configuro el modo oscuro de la app si el usuario lo ha seleccionado.
        val isDarkModeEnabled = sharedPreferences?.getBoolean("pref_modo_oscuro", false) ?: false

        if (isDarkModeEnabled){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}