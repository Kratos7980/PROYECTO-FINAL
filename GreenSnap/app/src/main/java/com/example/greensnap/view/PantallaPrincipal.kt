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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.adapter.JardinAdapter
import com.example.greensnap.databinding.ActivityPantallaPrincipalBinding
import com.example.greensnap.dbconexion.JardinHelper
import com.example.greensnap.model.Planta
import com.google.android.material.appbar.MaterialToolbar

class PantallaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPrincipalBinding
    private lateinit var myAdapter:JardinAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Muestro el menú
        val toolbar: MaterialToolbar = binding.menuPrincipal
        setSupportActionBar(toolbar)

        //Recupero la lista de plantas
        val listPlantas:ArrayList<Planta> = JardinHelper.recuperarPlantasJardin(this)

        //Muestro la lista de plantas en el recycler view
        val rv: RecyclerView = binding.rvItemsList

        //Creo el adaptador
        myAdapter = JardinAdapter(listPlantas, this)

        //Añado el adaptador al recycler view
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = myAdapter

    }

    // Funcion para mostrar el menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    // Funcion para controlar los eventos del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val preferences: SharedPreferences = getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        return when (item.itemId) {

            R.id.item_add -> {
                // Cambio a la activity de añadir planta
                intent = Intent(this, PantallaPlantas::class.java)
                startActivity(intent)
                finish()
                true
            }

            R.id.item_del -> {
                Toast.makeText(this, "Para eliminar mantén pulsada la planta.", Toast.LENGTH_LONG)
                    .show()
                false
            }

            R.id.item_home -> {
                false
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