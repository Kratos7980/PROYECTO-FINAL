package com.example.greensnap.dbconexion

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import com.example.greensnap.model.Planta

object PlantasHelper {

    fun recuperarPlantasBD(context:AppCompatActivity):ArrayList<Planta>{

        var planta:Planta? = null
        val dbManager = DBManager(context)
        val bd:SQLiteDatabase = dbManager.readableDatabase
        val listPlantas:ArrayList<Planta> = ArrayList<Planta>()
        val fila = bd.rawQuery("select * from PLANTAS ", arrayOf())

        if (fila.moveToFirst()){
            do {
                planta = Planta(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4))

                listPlantas.add(planta)
            }while(fila.moveToNext())
        }

        return listPlantas
    }
}