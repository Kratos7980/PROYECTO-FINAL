package com.example.greensnap.dbconexion

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.greensnap.model.Planta

object JardinHelper {

    fun recuperarPlantasJardin(context: Context):ArrayList<Planta>{
        //Creo un objeto planta
        var planta:Planta? = null

        //Instancio la base de datos
        val dbManager = DBManager(context)

        //Abro la base de datos
        val bd: SQLiteDatabase = dbManager.readableDatabase

        //Creo una lista de plantas
        val listPlantas:ArrayList<Planta> = ArrayList<Planta>()

        //Recupero los datos de la base de datos
        val fila = bd.rawQuery("select id_planta, nombre_planta, imagen from JARDIN ", arrayOf())

        //Si hay plantas en la base de datos, creo un objeto planta y lo añado a la lista.
        if (fila.moveToFirst()){
            do {
                planta = Planta(fila.getInt(0), fila.getString(1), fila.getString(2))
                listPlantas.add(planta)
            }while(fila.moveToNext())
        }
        //Cierro la conexión a la base de datos
        bd.close()

        return listPlantas
    }

    fun addPlanta(planta: Planta, context: Context){
        val dbManager = DBManager(context)
        val db = dbManager.writableDatabase
        val values = ContentValues()
        values.put("NOMBRE_PLANTA", planta.nombre_planta)
        values.put("IMAGEN", planta.imagen)
        db.insert("JARDIN", null, values)
        db.close()
    }

    fun removePlanta(planta:Planta, context: Context):Boolean{
        val dbManager = DBManager(context)
        val db = dbManager.writableDatabase
        val rowsDeleted = db.delete("JARDIN", "NOMBRE_PLANTA = ?", arrayOf(planta.nombre_planta))
        db.close()

        return rowsDeleted > 0
    }

}