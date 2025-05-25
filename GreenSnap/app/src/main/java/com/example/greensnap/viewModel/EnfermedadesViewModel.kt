package com.example.greensnap.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.greensnap.dbconexion.EnfermedadesHelper
import com.example.greensnap.model.Enfermedad
import com.example.greensnap.model.EnfermedadType

class EnfermedadesViewModel(application:Application): AndroidViewModel(application) {

    var context = application.applicationContext

    fun getEnfermedadesPlanta(cod_enfermedad: Int):ArrayList<EnfermedadType>{
        return EnfermedadesHelper.obtenerTipoEnfermedadesPlanta(cod_enfermedad, context)
    }

    fun getEnfermedadPlanta(cod_enfermedad:Int,id_tipo:Int): Enfermedad?{
        return EnfermedadesHelper.obtenerEnfermedadPlanta(cod_enfermedad,id_tipo, context)
    }
}