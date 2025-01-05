package com.example.greensnap.dbconexion

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(context:Context, dbName:String, factory:SQLiteDatabase.CursorFactory?, dbVersion:Int): SQLiteOpenHelper(context,dbName,factory,dbVersion) {

    companion object{
        val DB_NAME = "greensnap.db3"
        val DB_VERSION = 1
    }

    constructor(context:Context):this(context, DB_NAME, null, DB_VERSION){}

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table jardin (id_planta primary key autoincrement, nombre_planta text, tipo text);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists jardin;")
    }

}