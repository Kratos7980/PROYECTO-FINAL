package com.example.greensnap.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greensnap.R
import com.example.greensnap.adapter.PargerAdapter
import com.example.greensnap.databinding.ActivityPantallaCuidadosBinding
import com.example.greensnap.model.Planta
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PantallaCuidados : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaCuidadosBinding


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaCuidadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra("datos")
        val planta = bundle?.getSerializable("planta", Planta::class.java) as Planta

        // Muestro el menú
        val toolbar: MaterialToolbar? = binding.menuPrincipal
        setSupportActionBar(toolbar)

        //Instancio el tabLayout y el viewPager
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewParger

        //Creo un adaptador para el viewPager
        viewPager.adapter = PargerAdapter(planta,this )

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

    // Funcion para mostrar el menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fragment,menu)
        return true
    }
    // Funcion para controlar los eventos del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val preferences: SharedPreferences = getSharedPreferences("sesiones", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        return when (item.itemId) {


            R.id.item_home -> {
                // Cambio a la activity principal
                intent = Intent(this, PantallaPrincipal::class.java)
                startActivity(intent)
                finish()
                true
            }

            R.id.item_web -> {
                // Intento abrir la web de plantasmania
                val webIntent: Intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.plantasmania.com/"))
                val activities = this.packageManager.queryIntentActivities(
                    intent,
                    PackageManager.MATCH_DEFAULT_ONLY
                )

                // Compruebo que la web se pueda abrir.
                val isIntentSafe = activities.size > 0
                if (isIntentSafe) {
                    startActivity(webIntent)
                }
                true
            }

            R.id.item_preferences -> {
                // Cambio a la activity de preferencias
                intent = Intent(this, PantallaPreferences::class.java)
                startActivity(intent)
                true
            }

            R.id.item_logout -> {
                // Cierro sesion y cambio a la activity de login
                editor.putBoolean("sesion", false)
                editor.apply()
                Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_SHORT).show()
                intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
                true
            }

            else -> false
        }
    }
}