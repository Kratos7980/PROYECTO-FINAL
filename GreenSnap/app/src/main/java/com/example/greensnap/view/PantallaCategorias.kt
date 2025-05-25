package com.example.greensnap.view

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.adapter.CategoriasAdapter
import com.example.greensnap.databinding.ActivityPantallaCategoriasBinding
import com.example.greensnap.viewModel.CategoriasViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class PantallaCategorias : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaCategoriasBinding
    private lateinit var myAdapter: CategoriasAdapter
    private lateinit var bottomNavigation: BottomNavigationView
    private val viewModelCategorias:CategoriasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaCategoriasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigation = binding.bottomNavigationView
        bottomNavigation?.selectedItemId = R.id.item_guia
        bottomNavigation?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_home -> {
                    var intent = Intent(this, PantallaPrincipal::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    finish()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true
                }
                R.id.item_guia -> {
                    true
                }
                R.id.item_ubicacion -> {
                    var intent = Intent(this, PantallaUbicacion::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    finish()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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

        val listCategorias = viewModelCategorias.getCategorias()

        val rv: RecyclerView = binding.rvItemsCategorias

        myAdapter = CategoriasAdapter(listCategorias, this)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = myAdapter

    }

    override fun onResume() {
        super.onResume()
        bottomNavigation.setSelectedItemId(R.id.item_guia)
    }
}