package com.example.greensnap.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.greensnap.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener  {

    // Instancio el preferenceManager
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
    // Registro los cambios de preferencias.
    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }
    // Controlo el cambio de preferencias.
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

        when (key){
            "pref_modo_oscuro" -> {
                val isDarkModeEnabled = sharedPreferences?.getBoolean("pref_modo_oscuro", false)?:false
                if(isDarkModeEnabled){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                Toast.makeText(this.context, "Modo Oscuro: $isDarkModeEnabled", Toast.LENGTH_SHORT).show()
            }
            "pref_usuario" -> {
                var userName = sharedPreferences?.getString(key, "usuario") ?: "usuario"
                if(userName.isBlank()){
                    sharedPreferences?.edit()?.putString(key, "usuario")?.apply()
                }
                Log.e("Carlos", "valor por defecto: $userName")
            }
        }
    }
}