package com.example.greensnap.dbconexion

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import com.example.greensnap.model.Categoria

object CategoriasHelper {

    // Recupero la lista de categorías.
    fun obtenerCategorias(context: AppCompatActivity): ArrayList<Categoria> {

        // Creo un objeto categoría.
        var categoria:Categoria? = null

        // Instancio la base de datos.
        val dbManager = DBManager(context)

        // Abro la base de datos.
        val bd:SQLiteDatabase = dbManager.readableDatabase

        // Creo la lista de categorías.
        val listCategorias = ArrayList<Categoria>()

        // Recupero los datos de la base de datos.
        val fila = bd.rawQuery("SELECT * FROM CATEGORIAS", arrayOf())

        // Si hay categorías en la base de datos, creo un objeto categoría y lo añado a la lista.
        if (fila.moveToFirst()) {
            do{
                categoria = Categoria(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getInt(3))
                listCategorias.add(categoria)
            }while(fila.moveToNext())

        }
        // Cierro la conexión a la base de datos.
        bd.close()

        return listCategorias
    }
}