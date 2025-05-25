package com.example.greensnap.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.adapter.UbicacionAdapter
import com.example.greensnap.databinding.ActivityPantallaUbicacionBinding
import com.example.greensnap.model.Ubicacion
import com.example.greensnap.viewModel.CategoriasViewModel
import com.example.greensnap.viewModel.UbicacionViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.osmdroid.config.Configuration

class PantallaUbicacion : AppCompatActivity() {

    private val MULTIPLE_PERMISSION_REQUEST_CODE: Int = 4
    private lateinit var fusedLocationClient:FusedLocationProviderClient
    private lateinit var binding: ActivityPantallaUbicacionBinding
    private lateinit var myAdapter: UbicacionAdapter
    private lateinit var bottomNavigation: BottomNavigationView
    private val viewModel:UbicacionViewModel by viewModels()
    private val viewModelCategoria:CategoriasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaUbicacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigation = binding.bottomNavigationView
        bottomNavigation?.selectedItemId = R.id.item_ubicacion
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
                    var intent = Intent(this, PantallaCategorias::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    true
                }
                R.id.item_ubicacion -> {
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


        // Inicializa FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        Configuration.getInstance().setUserAgentValue(packageName)

        //Instancio el RecyclerView
        val rv: RecyclerView = binding.rvUbicaciones

        //Recupero la lista de ubicaciones
        val listUbicaciones:ArrayList<Ubicacion> = viewModel.getUbicaciones()

        //Creo el adaptador
        myAdapter = UbicacionAdapter(listUbicaciones, viewModelCategoria,this)

        //Añado el adaptador al recycler view.
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = myAdapter

        // Solicitar los permisos.
        checkPermissionsState()

        //Function boton add ubicacion:
        val btAddUbi = binding.btAddUbi
        btAddUbi.setOnClickListener {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                myLocations() { ubicacion->
                    if (ubicacion != null) {
                        val bundle = Bundle()
                        bundle.putSerializable("ubicacion", ubicacion)
                        intent = Intent(this, PantallaFormUbic::class.java)
                        intent.putExtra("datos",bundle)
                        startActivity(intent)
                    }
                }
            } else {
                Toast.makeText(this, "Permiso de ubicación no concedido", Toast.LENGTH_SHORT).show()
            }
        }
//        updateRecyclerView(id_categoria)
    }

//    private fun updateRecyclerView(id_categoria: Int) {
//        viewModel.getUbicacion(id_categoria){
//            ubicaciones -> myAdapter.updateList(ubicaciones)
//        }
//    }

    private fun checkPermissionsState() {
        val fineLocationPermissionCheck = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (fineLocationPermissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                ),
                MULTIPLE_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MULTIPLE_PERMISSION_REQUEST_CODE -> {
                if (grantResults.size > 0) {
                    var somePermissionWasDenied = false
                    for (result in grantResults) {
                        if (result == PackageManager.PERMISSION_DENIED) {
                            somePermissionWasDenied = true
                        }
                    }
                    if (somePermissionWasDenied) {
                        Toast.makeText(this, "Cant load maps without all the permissions granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Can load maps without all the permissions granted", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    private fun myLocations(callback: (Ubicacion?) -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val ubicacion = Ubicacion(it.latitude, it.longitude)
                    callback(ubicacion)
                } ?: run{
                    Log.e("ErrorUbicacion","Ubicacion no encontrada")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNavigation.setSelectedItemId(R.id.item_ubicacion)
    }
}