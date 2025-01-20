package com.example.greensnap.dbconexion

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import com.example.greensnap.model.Usuario

object UsuariosHelper {

    //Recupero la lista de usuarios
    fun recuperarUsuarioBD(context: AppCompatActivity):ArrayList<Usuario>{

        // Creo un objeto usuario.
        var usuario:Usuario? = null

        //Instancio la base de datos
        val dbManager = DBManager(context)

        //Abro la base de datos
        val bd: SQLiteDatabase = dbManager.readableDatabase

        //Creo una lista de usuarios
        val listUsuarios:ArrayList<Usuario> = ArrayList<Usuario>()

        //Recupero los datos de la base de datos
        val fila = bd.rawQuery("select * from USUARIOS ", arrayOf())

        //Si hay usuarios en la base de datos, creo un objeto usuario y lo añado a la lista.
        if (fila.moveToFirst()){
            do {
                usuario = Usuario(fila.getInt(0), fila.getString(1), fila.getString(2), fila.getString(3))
                listUsuarios.add(usuario)
            }while(fila.moveToNext())
        }

        return listUsuarios
    }
}