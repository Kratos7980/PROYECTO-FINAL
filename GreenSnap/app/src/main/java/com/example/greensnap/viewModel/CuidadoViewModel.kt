package com.example.greensnap.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.greensnap.dbconexion.CuidadosHelper
import com.example.greensnap.model.Cuidado

class CuidadoViewModel (private val context: Context) : ViewModel() {

    fun getCuidado(codigo_cuidado:Int): Cuidado?{
        return CuidadosHelper.obtenerCuidados(codigo_cuidado,context)
    }
}