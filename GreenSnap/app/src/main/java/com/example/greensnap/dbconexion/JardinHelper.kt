package com.example.greensnap.dbconexion

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.widget.Toast
import com.example.greensnap.model.Planta
import com.example.greensnap.model.PlantaJardin
import kotlin.jvm.Throws

object JardinHelper {

    fun recuperarPlantasJardin(context: Context):ArrayList<PlantaJardin>{
        //Creo un objeto planta
        var planta:PlantaJardin? = null

        //Instancio la base de datos
        val dbManager = DBManager(context)

        //Abro la base de datos
        val bd: SQLiteDatabase = dbManager.readableDatabase

        //Creo una lista de plantas
        val listPlantas:ArrayList<PlantaJardin> = ArrayList<PlantaJardin>()

        //Recupero los datos de la base de datos
        val fila = bd.rawQuery("SELECT NOMBRE_PLANTA, NOMBRE_CIENTIFICO, IMAGEN, ID_CATEGORIA FROM JARDIN", arrayOf())

        //Si hay plantas en la base de datos, creo un objeto planta y lo añado a la lista.
        if (fila.moveToNext()){
            do {
                planta = PlantaJardin(fila.getString(0),fila.getString(1), fila.getBlob(2), fila.getInt(3))
                listPlantas.add(planta)
            }while(fila.moveToNext())
        }
        //Cierro la conexión a la base de datos
        bd.close()

        return listPlantas
    }
    @Throws(SQLiteConstraintException::class)
    fun addPlanta(planta: PlantaJardin, context: Context) {
        val dbManager = DBManager(context)
        val db = dbManager.writableDatabase
        val values = ContentValues()
        values.put("NOMBRE_PLANTA", planta.nombre_planta)
        values.put("NOMBRE_CIENTIFICO", planta.nombre_cientifico)
        values.put("IMAGEN", planta.imagen)
        values.put("ID_CATEGORIA", planta.id_categoria)
        db.insertOrThrow("JARDIN", null, values)
        db.close()
    }

    fun removePlanta(planta:PlantaJardin, context: Context):Boolean{
        val dbManager = DBManager(context)
        val db = dbManager.writableDatabase
        val rowsDeleted = db.delete("JARDIN", "NOMBRE_PLANTA = ?", arrayOf(planta.nombre_planta))
        db.close()

        return rowsDeleted > 0
    }

}