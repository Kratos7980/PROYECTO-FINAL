package com.example.greensnap.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.greensnap.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener  {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val sharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val editor = sharedPreferences2?.edit()
        when (key){
            "pref_checkbox" -> {
                val isNotificationEnabled = sharedPreferences?.getBoolean(key, true) ?: true
            }
            "pref_usuario" -> {
                var userName = sharedPreferences?.getString(key, "usuario2") ?: "usuario3"
                if(userName.isBlank()){
                    userName = "usuario2"
                    editor?.putString(key, userName)
                        editor?.apply()
                }
                Log.e("Carlos", "valor por defecto: $userName")
            }
        }
    }
}