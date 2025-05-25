package com.example.greensnap.dbconexion

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.greensnap.model.Ubicacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object UbicacionHelper {

    suspend fun insertarUbicacion(context: Context, ubicacion: Ubicacion) {

        //Instancio la base de datos
        val dbManager = DBManager(context)

        //Abro la base de datos
        val db:SQLiteDatabase = dbManager.writableDatabase

        //Inserto los datos en la base de datos
        val values = ContentValues()
        values.put("NOMBRE_PLANTA", ubicacion.nombre_planta)
        values.put("ID_CATEGORIA", ubicacion.id_categoria)
        values.put("IMAGEN", ubicacion.imagen)
        values.put("LATITUD", ubicacion.latitud)
        values.put("LONGITUD", ubicacion.longitud)
        db.insertOrThrow("UBICACIONES", null, values)
        db.close()

    }

    suspend fun getUbicaciones(context: Context, id_categoria: Int): ArrayList<Ubicacion> {

        return withContext(Dispatchers.IO) {
            //Creo un objeto ubicacion
            var ubicacion:Ubicacion? = null

            //Creo una lista de ubicaciones
            val listUbicaciones:ArrayList<Ubicacion> = ArrayList<Ubicacion>()

            //Instancio la base de datos
            val dbManager = DBManager(context)

            // Abro la base de datos
            val db:SQLiteDatabase = dbManager.readableDatabase

            //Recupero los datos de la base de datos
            val fila = db.rawQuery("SELECT NOMBRE_PLANTA, ID_CATEGORIA, IMAGEN, LATITUD, LONGITUD FROM UBICACIONES WHERE ID_CATEGORIA = ?", arrayOf(id_categoria.toString()))

            //Si hay ubicaciones en la base de datos, creo un objeto ubicacion y lo añado a la lista.
            if (fila.moveToFirst()) {
                do {
                    ubicacion = Ubicacion(fila.getString(0),fila.getInt(1),fila.getBlob(2),fila.getDouble(3), fila.getDouble(4))
                    listUbicaciones.add(ubicacion)
                } while (fila.moveToNext())
            }

            //Devuelvo la lista de ubicaciones
            listUbicaciones
        }
    }

    fun getAllUbicaciones(context: Context): ArrayList<Ubicacion> {
            //Creo un objeto ubicacion
            var ubicacion:Ubicacion? = null

            //Creo una lista de ubicaciones
            val listUbicaciones:ArrayList<Ubicacion> = ArrayList<Ubicacion>()

            //Instancio la base de datos
            val dbManager = DBManager(context)

            // Abro la base de datos
            val db:SQLiteDatabase = dbManager.readableDatabase

            //Recupero los datos de la base de datos
            val fila = db.rawQuery("SELECT NOMBRE_PLANTA, ID_CATEGORIA, IMAGEN, LATITUD, LONGITUD FROM UBICACIONES", null)

            //Si hay ubicaciones en la base de datos, creo un objeto ubicacion y lo añado a la lista.
            if (fila.moveToFirst()) {
                do {
                    ubicacion = Ubicacion(fila.getString(0),fila.getInt(1),fila.getBlob(2),fila.getDouble(3), fila.getDouble(4))
                    listUbicaciones.add(ubicacion)
                } while (fila.moveToNext())
            }

            //Devuelvo la lista de ubicaciones
            return listUbicaciones
    }
}