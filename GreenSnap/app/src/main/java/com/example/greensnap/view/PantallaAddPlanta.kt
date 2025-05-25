package com.example.greensnap.view

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import android.R
import com.example.greensnap.databinding.ActivityPantallaAddPlantaBinding
import com.example.greensnap.model.Planta
import com.example.greensnap.model.PlantaJardin
import com.example.greensnap.viewModel.JardinViewModel
import com.example.greensnap.viewModel.PlantasViewModel
import java.io.ByteArrayOutputStream

class PantallaAddPlanta : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaAddPlantaBinding
    private lateinit var bitmap: Bitmap
    private val viewModelJardin:JardinViewModel by viewModels()
    private val viewModelPlantas: PlantasViewModel by viewModels()
    private lateinit var planta:Planta
    private lateinit var plantaJ:PlantaJardin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaAddPlantaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listPlantas = viewModelPlantas.getPlantas()
        var cbPlanta = binding.cbPlantas;
        var listNombres = ArrayList<String>()
        for(planta in listPlantas){
            listNombres.add(planta.nombre_planta)
        }

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, listNombres)
        cbPlanta.setAdapter(adapter)


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

        binding.btnCamara.setOnClickListener {

            requestCameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }

        binding.btnGaleria.setOnClickListener{

            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        cbPlanta.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, cbPlanta.adapter.getItem(position).toString(), Toast.LENGTH_SHORT).show()
            planta = listPlantas.find { p -> p.nombre_planta == cbPlanta.adapter.getItem(position).toString() }!!
            if(planta != null){
                plantaJ = PlantaJardin(binding.inputNombre.text.toString(),cbPlanta.adapter.getItem(position).toString(),planta.id_categoria)
            }

        }

        binding.btnCrear.setOnClickListener {
            bitmap = binding.img.drawable.toBitmap()
            var stream= ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
            var imagen:ByteArray = stream.toByteArray()
            if(plantaJ != null){
                plantaJ.imagen = imagen
            }
            AlertDialog(plantaJ,planta)
        }
    }

    private fun AlertDialog(planta: PlantaJardin, plantaInfo:Planta) {
        android.app.AlertDialog.Builder(this)
            .setTitle("Confirmación")
            .setMessage("¿Estás seguro de que deseas añadir esta planta?")
            .setPositiveButton("SI",) { dialog, which ->
                var insertado = false
                try{
                    viewModelJardin.addPlanta(planta)
                    insertado = true
                }catch (ex: SQLiteConstraintException){
                    Toast.makeText(this, "Ya existe una planta con ese nombre.", Toast.LENGTH_LONG).show()
                }
                if(insertado){
                    var bundle = Bundle()
                    bundle.putSerializable("planta", plantaInfo)
                    intent = Intent(this, PantallaPrincipal::class.java)
                    intent.putExtra("datos", bundle)
                    startActivity(intent)
                    finish()
                }

            }
            .setNegativeButton("NO", ){ dialog, which ->
                Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
                intent = Intent(this, PantallaPrincipal::class.java)
                startActivity(intent)
                finish()
            }
            .show()
    }
}