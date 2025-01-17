package com.example.greensnap.dbconexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.ContextCompat.getString
import com.example.greensnap.R

class DBManager(private val context:Context, dbName:String, factory:SQLiteDatabase.CursorFactory?, dbVersion:Int): SQLiteOpenHelper(context,dbName,factory,dbVersion) {

    companion object{
        val DB_NAME = "greensnap.db3"
        val DB_VERSION = 3
    }

    constructor(context:Context):this(context, DB_NAME, null, DB_VERSION){}

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table plantas(id_planta integer primary key autoincrement, nombre_planta text, tipo text, imagen text, id_categoria integer)")
        db.execSQL("insert into plantas (nombre_planta, tipo, imagen, id_categoria) values('"+getString(context, R.string.azalea_hortino)+"','"+getString(context, R.string.interior)+"'," + "'azalea_hortinno'," + 1 + ")")
        db.execSQL("create table jardin (id_planta integer primary key autoincrement, nombre_planta text, tipo text);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.execSQL("drop table if exists jardin")
//        db.execSQL("drop table if exists plantas")
//        db.execSQL("create table plantas(id_planta integer primary key autoincrement, nombre_planta text, tipo text, imagen text, id_categoria integer)")
//        db.execSQL("create table jardin (id_planta integer primary key autoincrement, nombre_planta text, tipo text);")
    }

}