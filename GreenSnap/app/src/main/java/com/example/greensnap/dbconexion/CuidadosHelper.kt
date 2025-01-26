package com.example.greensnap.dbconexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.greensnap.model.Cuidado

object CuidadosHelper {

    fun obtenerCuidados(codigo_cuidado:Int?, context:Context):Cuidado?{

        // Creo la variable donde se guardará el cuidado.
        var cuidado:Cuidado? = null

        // Instancio la base de datos.
        val dbManager = DBManager(context)

        // Abro la base de datos.
        val bd:SQLiteDatabase = dbManager.readableDatabase

        // Recupero los datos de la base de datos.
        val fila = bd.rawQuery("SELECT * FROM CUIDADOS WHERE codigo_cuidados = ?", arrayOf(codigo_cuidado.toString()))

        // Si hay categorías en la base de datos, creo un objeto categoría y lo añado a la lista.
        if(fila.moveToFirst()){
            cuidado = Cuidado(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getString(4), fila.getString(5), fila.getString(6), fila.getString(7), fila.getString(8), fila.getString(9))
        }
        //Cerramos la conexion a la base de datos
        bd.close()

        return cuidado
    }
}