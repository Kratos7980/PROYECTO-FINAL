package com.example.greensnap.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.greensnap.dbconexion.CuidadosHelper
import com.example.greensnap.model.Cuidado
import com.example.greensnap.model.CuidadoType

class CuidadoViewModel (application: Application) : AndroidViewModel(application) {

    var context:Context;
    init {
        context = application.applicationContext
    }
    fun getCuidadoPlanta(codigo_cuidado:Int): Cuidado?{
        return CuidadosHelper.obtenerCuidadosPlanta(codigo_cuidado,context)
    }

    fun getCuidados():ArrayList<CuidadoType>{
        return CuidadosHelper.obtenerCuidados(context)
    }
}