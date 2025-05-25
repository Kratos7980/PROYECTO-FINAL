package com.example.greensnap.viewModel

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.AndroidViewModel
import com.example.greensnap.dbconexion.JardinHelper
import com.example.greensnap.model.PlantaJardin

class JardinViewModel (application: Application) : AndroidViewModel(application) {

    var context: Context
    init {
        context = application.applicationContext
    }

    @Throws(SQLiteConstraintException::class)
    fun addPlanta(planta: PlantaJardin){
        JardinHelper.addPlanta(planta, context)
    }
}