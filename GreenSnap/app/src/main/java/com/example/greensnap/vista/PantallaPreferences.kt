package com.example.greensnap.vista

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceManager
import com.example.greensnap.R
import com.example.greensnap.databinding.ActivityPantallaPreferencesBinding

class PantallaPreferences : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaPreferencesBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        //modo nocturno
        val isDarkModeEnabled = sharedPreferences?.getBoolean("pref_modo_oscuro", false) ?: false
        Toast.makeText(this, "PantallaPreferences-Modo Nocturno: $isDarkModeEnabled ", Toast.LENGTH_SHORT).show()
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