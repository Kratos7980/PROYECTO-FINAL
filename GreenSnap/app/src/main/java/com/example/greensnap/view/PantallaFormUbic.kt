package com.example.greensnap.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.os.Bundle
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.example.greensnap.databinding.ActivityPantallaFormUbicBinding
import com.example.greensnap.model.Categoria
import com.example.greensnap.model.Ubicacion
import com.example.greensnap.viewModel.CategoriasViewModel
import com.example.greensnap.viewModel.UbicacionViewModel
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.MinimapOverlay
import org.osmdroid.views.overlay.ScaleBarOverlay
import org.osmdroid.views.overlay.compass.CompassOverlay
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import java.io.ByteArrayOutputStream

class PantallaFormUbic : AppCompatActivity(), MapEventsReceiver {

    private lateinit var binding:ActivityPantallaFormUbicBinding
    private lateinit var bitmap: Bitmap
    private val viewModelUbicacion: UbicacionViewModel by viewModels()
    private val viewModelCategorias: CategoriasViewModel by viewModels()
    private lateinit var categoria:Categoria
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaFormUbicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapView = binding.mapa

        val bundle = intent.getBundleExtra("datos")
        val ubicacion = bundle?.getSerializable("ubicacion") as Ubicacion

        val listCategorias = viewModelCategorias.getCategorias()
        var cbCategorias = binding.cbPlantas;
        var listNombres = ArrayList<String>()
        for(categoria in listCategorias){
            listNombres.add(categoria.nombre)
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listNombres)
        cbCategorias.setAdapter(adapter)


        val openCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data!!
                bitmap = data.extras!!.get("data") as Bitmap
                binding.img.setImageBitmap(bitmap)
            }
        }

        val requestCameraPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission())
            {
                    isGranted: Boolean ->
                if (isGranted) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    openCamera.launch(intent)
                } else {
                    Log.e("CARLOS","Permiso de cámara no concedido")
                }
            }

        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
                    uri ->

                if (uri != null) {
                    Log.d("CARLOS", "Selected URI: $uri")
                    binding.img.setImageURI(uri)
                    Log.d("CARLOS", "Cargada")
                } else {
                    Log.d("CARLOS", "No media selected")
                }
            }

        setupMap()
        myLocation()

        binding.btnCamara.setOnClickListener {

            requestCameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }

        binding.btnGaleria.setOnClickListener{

            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.btnCrear.setOnClickListener {
            bitmap = binding.img.drawable.toBitmap()
            var stream= ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
            var imagen:ByteArray = stream.toByteArray()

            categoria = listCategorias.find { p -> p.nombre == cbCategorias.adapter.getItem(0).toString() }!!
            ubicacion.imagen = imagen
            ubicacion.id_categoria = categoria.id
            ubicacion.nombre_planta = binding.inputNombre.text.toString()

            AlertDialog(ubicacion)

        }

    }

    private fun AlertDialog(ubicacion: Ubicacion) {
        android.app.AlertDialog.Builder(this)
            .setTitle("Confirmación")
            .setMessage("¿Estás seguro de que deseas añadir esta planta?")
            .setPositiveButton("SI",) { dialog, which ->
                viewModelUbicacion.insertarUbicacion(ubicacion)
                intent = Intent(this, PantallaUbicacion::class.java)
                startActivity(intent)
                finish()


            }
            .setNegativeButton("NO", ){ dialog, which ->
                Toast.makeText(this, "Operación cancelada", Toast.LENGTH_LONG).show()
                intent = Intent(this, PantallaUbicacion::class.java)
                startActivity(intent)
                finish()
            }
            .show()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    private fun setupMap(){
        //Inicializar map
        mapView.setClickable(true);
        mapView.setDestroyMode(false);
        mapView.setTileSource(TileSourceFactory.MAPNIK) // Define la fuente de mosaicos
        mapView.setMultiTouchControls(true)// Habilita los controles multitáctiles
        mapView.getLocalVisibleRect(Rect())

        //superponer brújula
        var compassOverlay = CompassOverlay(this, InternalCompassOrientationProvider(this), mapView)
        compassOverlay.enableCompass()
        mapView.overlays.add(compassOverlay)

        //agregar barra de escala en el mapa
        val dm : DisplayMetrics = this.resources.displayMetrics
        val scaleBarOverlay = ScaleBarOverlay(mapView)
        scaleBarOverlay.setCentred(true)
        //ubicacion en la app de la barra de escala
        scaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 40)
        mapView.overlays.add(scaleBarOverlay)

    }

    private fun myLocation() {

        var mLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), mapView)
        mLocationOverlay.enableMyLocation()
        //si deseas que el mapa siga la ubicación del usuario
        mLocationOverlay.enableFollowLocation()
        mLocationOverlay.runOnFirstFix {
            runOnUiThread {
                //cuando tengamos ubicación
                //Centrar y hacer Zoom hacia un marcador
                mapView.controller.setCenter(mLocationOverlay.myLocation);
                mapView.controller.animateTo(mLocationOverlay.myLocation)
                mapView.controller.setZoom(18.0)
                mapView.invalidate() // Redibujar el mapa
            }
        }
        mapView.overlays.add(mLocationOverlay)
    }

    override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
        return false
    }

    override fun longPressHelper(p: GeoPoint?): Boolean {
        return false
    }
}