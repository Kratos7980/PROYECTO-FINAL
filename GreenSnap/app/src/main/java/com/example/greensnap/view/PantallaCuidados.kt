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
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.preference.PreferenceManager
import com.example.greensnap.R
import com.example.greensnap.adapter.PargerAdapter
import com.example.greensnap.databinding.ActivityPantallaCuidadosBinding
import com.example.greensnap.model.Planta
import com.example.greensnap.model.PlantaJardin
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class PantallaCuidados : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaCuidadosBinding
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var pantalla:String

    companion object{
        const val PLANTA_DATA = "PLANTA_DATA"
    }

    private var planta: Planta? = null


    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themeName = sharedPreferences.getString("pref_modo_claro","Theme.GreenSnap")?:"Theme.GreenSnap"
        when(themeName) {
            "Theme.GreenSnap" -> {
                setTheme(R.style.Theme_GreenSnap)
            }
        }

        val isDarkModeEnabled = sharedPreferences?.getBoolean("pref_modo_oscuro",false)?:false
        if(isDarkModeEnabled){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityPantallaCuidadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pantalla = ""
        val bundle = intent.getBundleExtra("datos")
        if(bundle != null){
            pantalla = bundle.getString("pantalla").toString()
            planta = bundle.getSerializable("planta") as Planta?
        }

        bottomNavigation = binding.bottomNavigationView
        if(!pantalla.isBlank()){
            if(pantalla.equals("jardin")){
                bottomNavigation?.selectedItemId = R.id.item_home
            }
            else if(pantalla.equals("guia")){
                bottomNavigation?.selectedItemId = R.id.item_guia
            }
        }

        bottomNavigation?.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
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

        //Instancio el tabLayout y el viewPager
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewParger

        //Creo un adaptador para el viewPager
        viewPager.adapter = PargerAdapter(planta, this )

        //Creo un adaptador para el tabLayout, lo enlazo con el viewPager y pongo título a cada pestaña.
        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> {
                    "CUIDADOS"
                }

                1 -> {
                    "ENFERMEDADES"
                }
                2 ->{
                    "VIDEOCONSEJOS"
                }
                else -> {
                    throw Resources.NotFoundException("Position not found")
                }
            }
        }.attach()
    }

    @SuppressLint("NewApi")
    override fun onSaveInstanceState(savedInstanceState:Bundle) {
        super.onSaveInstanceState(savedInstanceState);
        // Save in savedInstanceState.
        savedInstanceState.putSerializable(PLANTA_DATA, planta)
    }

    override fun onRestoreInstanceState(savedInstanceState:Bundle) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state from the savedInstanceState.

        if (savedInstanceState != null) {
            planta = savedInstanceState.getSerializable(PLANTA_DATA) as Planta
        }
    }

    override fun onResume() {
        super.onResume()
        if(!pantalla.isBlank()){
            if(pantalla.equals("jardin")){
                bottomNavigation?.selectedItemId = R.id.item_home
            }
            else if(pantalla.equals("guia")){
                bottomNavigation?.selectedItemId = R.id.item_guia
            }
        }
    }

}