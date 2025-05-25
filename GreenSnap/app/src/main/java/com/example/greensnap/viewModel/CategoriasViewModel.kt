package com.example.greensnap.viewModel

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.greensnap.dbconexion.CategoriasHelper
import com.example.greensnap.dbconexion.DBManager
import com.example.greensnap.model.Categoria

class CategoriasViewModel(application: Application): AndroidViewModel(application) {

    private var context: Context
    init {
        context = application.applicationContext
    }

    fun getCategorias():ArrayList<Categoria>{
        return CategoriasHelper.getCategorias(context)
    }

    fun getCategoria(id_categoria:Int): Categoria{
        return CategoriasHelper.getCategoriaById(id_categoria,context)
    }
}