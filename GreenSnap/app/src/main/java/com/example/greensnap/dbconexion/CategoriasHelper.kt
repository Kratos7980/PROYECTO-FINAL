package com.example.greensnap.dbconexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.greensnap.model.Categoria

object CategoriasHelper {

    private lateinit var categoria: Categoria

    // Recupero la lista de categorías.
    fun getCategorias(context:Context):ArrayList<Categoria>{
        //Instancio la base de datos
        val dbManager = DBManager(context)
        //Abro la base de datos
        val bd: SQLiteDatabase = dbManager.readableDatabase
        //Creo la lista donde se guardarán las categorías
        val listCategorias = ArrayList<Categoria>()
        //Creo una categoria temporal
        var categoria: Categoria
        //Recupero los datos de la base de datos
        val fila = bd.rawQuery("SELECT * FROM CATEGORIAS", null)
        //Mientras haya registros en la base de datos
        while (fila.moveToNext()){
            //Creo una categoria con los datos del registro
            categoria = Categoria(fila.getInt(0),fila.getString(1),fila.getString(2))
            //Añado la categoria a la lista
            listCategorias.add(categoria)
        }
        //Cierro la conexión a la base de datos
        bd.close()
        //Devuelvo la lista de categorías
        return listCategorias
    }
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

    fun getCategoriaById(id_categoria:Int, context: Context):Categoria{

        // Instancio la base de datos.
        val dbManager = DBManager(context)
        // Abro la base de datos.
        val bd:SQLiteDatabase = dbManager.readableDatabase
        // Creo la variable donde se guardará la descripción.
        var descripcion = ""
        // Recupero los datos de la base de datos.
        val fila = bd.rawQuery("SELECT NOMBRE_CATEGORIA, IMAGEN FROM CATEGORIAS WHERE id_categoria = ?", arrayOf(id_categoria.toString()))
        // Si hay registros devueltos de la base de datos, guardo la descripción.
        if (fila.moveToNext()) {
            categoria = Categoria(id_categoria,fila.getString(0),fila.getString(1))
        }
        // Cierro la conexión a la base de datos.
        bd.close()

        return categoria
    }
}