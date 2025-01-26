package com.example.greensnap.dbconexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase

object CategoriasHelper {

    // Recupero la lista de categorías.
    fun obtenerCodigoCuidadosByIdCategoria(id_categoria:Int?, context: Context): Int{

        // Instancio la base de datos.
        val dbManager = DBManager(context)

        // Abro la base de datos.
        val bd:SQLiteDatabase = dbManager.readableDatabase

        // Creo la variable donde se guardará el id del cuidado.
        var codigo_cuidado = -1

        // Recupero los datos de la base de datos.
        val fila = bd.rawQuery("SELECT codigo_cuidados FROM CATEGORIAS WHERE id_categoria = ?", arrayOf(id_categoria.toString()))

        // Si hay registros devueltos de la base de datos, guardo el id del cuidado.
        if (fila.moveToFirst()) {
            codigo_cuidado = fila.getInt(0)
        }
        // Cierro la conexión a la base de datos.
        bd.close()

        return codigo_cuidado
    }
}