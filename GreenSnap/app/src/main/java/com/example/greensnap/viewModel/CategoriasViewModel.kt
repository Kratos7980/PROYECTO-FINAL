package com.example.greensnap.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.greensnap.dbconexion.CategoriasHelper

class CategoriasViewModel(private val context: Context): ViewModel() {

    fun getIdCuidado(id_categoria:Int?): Int{
        return CategoriasHelper.obtenerCodigoCuidadosByIdCategoria(id_categoria,context)
    }

    fun getDescripcionById(id_categoria:Int): String{
        return CategoriasHelper.getDescripcionById(id_categoria,context)
    }
}