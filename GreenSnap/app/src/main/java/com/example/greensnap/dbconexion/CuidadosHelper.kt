package com.example.greensnap.dbconexion

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import com.example.greensnap.model.Cuidado

object CuidadosHelper {

    fun obtenerCuidados(context:AppCompatActivity):ArrayList<Cuidado>{

        // Creo un objeto categoría.
        var cuidado:Cuidado? = null

        // Instancio la base de datos.
        val dbManager = DBManager(context)

        // Abro la base de datos.
        val bd:SQLiteDatabase = dbManager.readableDatabase

        // Recupero los datos de la base de datos.
        val fila = bd.rawQuery("SELECT * FROM CUIDADOS", arrayOf())

        // Creo la lista de categorías.
        val listaCuidados = ArrayList<Cuidado>()

        // Si hay categorías en la base de datos, creo un objeto categoría y lo añado a la lista.
        if(fila.moveToFirst()){
            do {
                cuidado = Cuidado(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getString(4), fila.getString(5), fila.getString(6), fila.getString(7), fila.getString(8), fila.getString(9))
                listaCuidados.add(cuidado)
            }while(fila.moveToNext())
        }
        //Cerramos la conexion a la base de datos
        bd.close()

        return listaCuidados
    }
}