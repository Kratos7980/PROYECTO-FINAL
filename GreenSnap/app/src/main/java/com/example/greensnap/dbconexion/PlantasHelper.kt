package com.example.greensnap.dbconexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.greensnap.model.Planta

object PlantasHelper {

    private lateinit var planta:Planta
    //Recupero la lista de plantas
    fun recuperarPlantasByIdCategoria(context:Context, id_categoria:Int):ArrayList<Planta>{

        //Instancio la base de datos
        val dbManager = DBManager(context)

        //Abro la base de datos
        val bd:SQLiteDatabase = dbManager.readableDatabase

        //Creo una lista de plantas
        val listPlantas:ArrayList<Planta> = ArrayList<Planta>()

        //Recupero los datos de la base de datos
        val fila = bd.rawQuery("select * from PLANTAS WHERE ID_CATEGORIA = ?", arrayOf(id_categoria.toString()))

        //Si hay plantas en la base de datos, creo un objeto planta y lo añado a la lista.
        if (fila.moveToFirst()){
            do {
                planta = Planta(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4), fila.getInt(5), fila.getInt(6))
                listPlantas.add(planta)
            }while(fila.moveToNext())
        }
        //Cierro la conexión a la base de datos
        bd.close()

        return listPlantas
    }

    fun recuperarPlantas(context:Context):ArrayList<Planta>{

        //Instancio la base de datos
        val dbManager = DBManager(context)

        //Abro la base de datos
        val bd:SQLiteDatabase = dbManager.readableDatabase

        //Creo una lista de plantas
        val listPlantas:ArrayList<Planta> = ArrayList<Planta>()

        //Recupero los datos de la base de datos
        val fila = bd.rawQuery("select * from PLANTAS", arrayOf())

        //Si hay plantas en la base de datos, creo un objeto planta y lo añado a la lista.
        while (fila.moveToNext()){
            planta = Planta(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4), fila.getInt(5), fila.getInt(6))
            listPlantas.add(planta)
        }
        //Cierro la conexión a la base de datos
        bd.close()

        return listPlantas
    }

    fun findByName(context:Context, nombre:String):Planta{
        //Instancio la base de datos
        val dbManager = DBManager(context)

        //Abro la base de datos
        val bd:SQLiteDatabase = dbManager.readableDatabase

        //Recupero los datos de la base de datos
        val fila = bd.rawQuery("SELECT ID_PLANTA, NOMBRE_PLANTA, IMAGEN, DESCRIPCION, ID_CATEGORIA, COD_CUIDADO, COD_ENFERMEDAD FROM PLANTAS WHERE NOMBRE_PLANTA = ?", arrayOf(nombre))

        //Si hay plantas en la base de datos, creo un objeto planta y lo añado a la lista.
        if (fila.moveToNext()){
            do {
                planta = Planta(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getInt(4), fila.getInt(5), fila.getInt(6))
            }while(fila.moveToNext())
        }
        //Cierro la conexión a la base de datos
        bd.close()

        return planta;
    }
}