package com.example.greensnap.dbconexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.greensnap.model.Enfermedad
import com.example.greensnap.model.EnfermedadType

object EnfermedadesHelper {

    // Recuperar la lista de enfermedades.
    fun obtenerTipoEnfermedadesPlanta(cod_enfermedad:Int, context: Context):ArrayList<EnfermedadType>{

        var enfermedad:EnfermedadType? = null

        var listEnfermedades:ArrayList<EnfermedadType> = ArrayList()

        val dbManager = DBManager(context)

        val bd: SQLiteDatabase = dbManager.readableDatabase

        val fila = bd.rawQuery("SELECT TE.ID_TIPO_ENFERMEDAD, TE.NOMBRE_TIPO_ENFERMEDAD, TE.IMAGEN FROM TIPO_ENFERMEDADES TE WHERE TE.ID_TIPO_ENFERMEDAD IN (SELECT ID_TIPO_ENFERMEDAD FROM ENFERMEDADES WHERE cod_enfermedad = ?)", arrayOf(cod_enfermedad.toString()))

        while(fila.moveToNext()){
            enfermedad = EnfermedadType(fila.getInt(0), fila.getString(1), fila.getString(2))
            listEnfermedades.add(enfermedad);
        }
        return listEnfermedades
    }

    fun obtenerEnfermedadPlanta(cod_enfermedad:Int,id_tipo:Int, context: Context):Enfermedad?{

        var enfermedad:Enfermedad? = null

        val dbManager = DBManager(context)

        val bd: SQLiteDatabase = dbManager.readableDatabase

        val fila = bd.rawQuery("SELECT COD_ENFERMEDAD,DESCRIPCION, SOLUCION, ID_TIPO_ENFERMEDAD FROM ENFERMEDADES WHERE cod_enfermedad = ? and id_tipo_enfermedad = ?", arrayOf(cod_enfermedad.toString(), id_tipo.toString()))

        if(fila.moveToNext()){
            enfermedad = Enfermedad(fila.getInt(0), fila.getString(1), fila.getInt(2))
        }
        return enfermedad

    }
}