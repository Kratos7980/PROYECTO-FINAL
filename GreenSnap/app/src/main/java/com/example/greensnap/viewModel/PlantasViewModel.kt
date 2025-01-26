package com.example.greensnap.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.greensnap.dbconexion.PlantasHelper
import com.example.greensnap.model.Planta

class PlantasViewModel (private val context: Context) : ViewModel() {

    fun getPlantas(): ArrayList<Planta>{
        return PlantasHelper.recuperarPlantasBD(context)
    }
}