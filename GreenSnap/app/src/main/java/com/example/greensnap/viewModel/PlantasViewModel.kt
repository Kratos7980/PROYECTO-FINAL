package com.example.greensnap.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.greensnap.dbconexion.PlantasHelper
import com.example.greensnap.model.Planta

class PlantasViewModel (application:Application) : AndroidViewModel(application) {
    var context:Context
    init {
        context = application.applicationContext
    }

    fun getPlantas():ArrayList<Planta>{
        return PlantasHelper.recuperarPlantas(context)
    }

    fun findByName(nombre:String):Planta{
        return PlantasHelper.findByName(context, nombre)
    }

    fun getPlantasByIdCategoria(id_categoria:Int): ArrayList<Planta>{
        return PlantasHelper.recuperarPlantasByIdCategoria(context, id_categoria)
    }
}