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
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.adapter.JardinAdapter
import com.example.greensnap.databinding.ActivityPantallaPrincipalBinding
import com.example.greensnap.dbconexion.JardinHelper
import com.example.greensnap.model.Planta
import com.example.greensnap.model.PlantaJardin
import com.example.greensnap.viewModel.PlantasViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class PantallaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPrincipalBinding
    private lateinit var myAdapter:JardinAdapter
    private lateinit var bottomNavigation: BottomNavigationView
    private val viewModelPlanta: PlantasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Muestro el menu
        val toolbar: MaterialToolbar? = binding.menuPrincipal
        setSupportActionBar(toolbar)

        bottomNavigation = binding.bottomNavigationView
        bottomNavigation?.selectedItemId = R.id.item_home
        bottomNavigation?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_home -> {
                    true
                }
                R.id.item_guia -> {
                    var intent = Intent(this, PantallaCategorias::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    true
                }
                R.id.item_ubicacion -> {
                    var intent = Intent(this, PantallaUbicacion::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    true
                }
                R.id.item_web -> {
                    // Intento abrir la web de plantasmania
                    val webIntent: Intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.picturethisai.com/es/"))
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
                else -> false
            }
        }

        //Recupero la lista de plantas
        val listPlantas:ArrayList<PlantaJardin> = JardinHelper.recuperarPlantasJardin(this)

        //Muestro la lista de plantas en el recycler view
        val rv: RecyclerView = binding.rvItemsList

        //Creo el adaptador
        myAdapter = JardinAdapter(listPlantas, viewModelPlanta, this)

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
        return when (item.itemId) {

            R.id.item_add -> {
                // Cambio a la activity de añadir planta
                intent = Intent(this, PantallaAddPlanta::class.java)
                startActivity(intent)
                true
            }

            R.id.item_del -> {
                Toast.makeText(this, "Para eliminar mantén pulsada la planta.", Toast.LENGTH_LONG)
                    .show()
                false
            }

            else -> false
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNavigation.setSelectedItemId(R.id.item_home)
    }

}