package com.example.greensnap.dbconexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.greensnap.model.Cuidado
import com.example.greensnap.model.CuidadoType

object CuidadosHelper {

    fun obtenerCuidadosPlanta(codigo_cuidado:Int?, context:Context):Cuidado?{

        // Creo la variable donde se guardará el cuidado.
        var cuidado:Cuidado? = null

        // Instancio la base de datos.
        val dbManager = DBManager(context)

        // Abro la base de datos.
        val bd:SQLiteDatabase = dbManager.readableDatabase

        // Recupero los datos de la base de datos.
        val fila = bd.rawQuery("SELECT COD_CUIDADO, DESCRIPCION, ID_TIPO_CUIDADO FROM CUIDADOS WHERE COD_CUIDADO = ?", arrayOf(codigo_cuidado.toString()))

        // Si hay categorías en la base de datos, creo un objeto categoría y lo añado a la lista.
        while(fila.moveToNext()){
            cuidado = Cuidado(fila.getInt(0), fila.getString(1), fila.getInt(2))
        }
        //Cerramos la conexion a la base de datos
        bd.close()

        return cuidado
    }

    fun obtenerCuidados(context:Context): ArrayList<CuidadoType>{
        var listaCuidados:ArrayList<CuidadoType> = ArrayList()

        val dbManager = DBManager(context)

        val bd:SQLiteDatabase = dbManager.readableDatabase

        val fila = bd.rawQuery("SELECT * FROM TIPO_CUIDADOS", null)

        while (fila.moveToNext()){
            listaCuidados.add(CuidadoType(fila.getInt(0),fila.getString(1),fila.getString(2)))
        }

        return listaCuidados

    }
}