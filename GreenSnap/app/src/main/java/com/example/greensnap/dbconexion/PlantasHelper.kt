package com.example.greensnap.dbconexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import com.example.greensnap.model.Planta

object PlantasHelper {
    //Recupero la lista de plantas
    fun recuperarPlantasBD(context:Context):ArrayList<Planta>{

        //Creo un objeto planta
        var planta:Planta? = null

        //Instancio la base de datos
        val dbManager = DBManager(context)

        //Abro la base de datos
        val bd:SQLiteDatabase = dbManager.readableDatabase

        //Creo una lista de plantas
        val listPlantas:ArrayList<Planta> = ArrayList<Planta>()

        //Recupero los datos de la base de datos
        val fila = bd.rawQuery("select * from PLANTAS ", arrayOf())

        //Si hay plantas en la base de datos, creo un objeto planta y lo añado a la lista.
        if (fila.moveToFirst()){
            do {
                planta = Planta(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4))

                listPlantas.add(planta)
            }while(fila.moveToNext())
        }
        //Cierro la conexión a la base de datos
        bd.close()

        return listPlantas
    }
}