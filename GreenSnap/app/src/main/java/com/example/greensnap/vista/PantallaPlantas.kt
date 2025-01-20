package com.example.greensnap.vista

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.adapter.PlantasAdapter
import com.example.greensnap.databinding.ActivityPantallaPlantasBinding
import com.example.greensnap.dbconexion.PlantasHelper
import com.example.greensnap.model.Planta

class PantallaPlantas() : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPlantasBinding
    private lateinit var myAdapter:PlantasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPlantasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listPlantas:ArrayList<Planta> = PlantasHelper.recuperarPlantasBD(this)
        val rv:RecyclerView = binding.rvItemsList
        myAdapter = PlantasAdapter(listPlantas, this)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = myAdapter
    }

}