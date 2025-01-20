package com.example.greensnap.view

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greensnap.R
import com.example.greensnap.adapter.PargerAdapter
import com.example.greensnap.databinding.ActivityPantallaCuidadosBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PantallaCuidados : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaCuidadosBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaCuidadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Instancio el tabLayout y el viewPager
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewParger

        //Creo un adaptador para el viewPager
        viewPager.adapter = PargerAdapter(this)

        //Creo un adaptador para el tabLayout, lo enlazo con el viewPager y pongo título a cada pestaña.
        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> {
                    "CUIDADOS"
                }

                1 -> {
                    "ENFERMEDADES"
                }
                else -> {
                    throw Resources.NotFoundException("Position not found")
                }
            }
        }.attach()
    }
}