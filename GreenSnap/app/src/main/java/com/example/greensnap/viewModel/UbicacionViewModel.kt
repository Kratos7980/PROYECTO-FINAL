package com.example.greensnap.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.greensnap.dbconexion.UbicacionHelper
import com.example.greensnap.model.Ubicacion
import kotlinx.coroutines.launch

class UbicacionViewModel(application: Application) : AndroidViewModel(application) {

    var context: Context
    init {
        context = application.applicationContext
    }

    fun insertarUbicacion(ubicacion: Ubicacion){
        viewModelScope.launch {
            UbicacionHelper.insertarUbicacion(context, ubicacion)
        }
    }

    fun getUbicacion(id_categoria:Int, callback: (ArrayList<Ubicacion>) -> Unit){
        viewModelScope.launch {
            val ubicaciones = UbicacionHelper.getUbicaciones(context, id_categoria)
            callback(ubicaciones)
        }
    }

    fun getUbicaciones(): ArrayList<Ubicacion>{
        return UbicacionHelper.getAllUbicaciones(context)
    }
}