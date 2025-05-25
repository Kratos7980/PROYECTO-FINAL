package com.example.greensnap.model

import java.io.Serializable

data class PlantaJardin(
    val nombre_planta: String,
    val nombre_cientifico:String,
    var imagen: ByteArray,
    val id_categoria: Int
):Serializable{
    constructor() : this("","", ByteArray(0), 0)
    constructor(nombre_planta: String, nombre_cientifico:String, id_categoria: Int) : this(nombre_planta, nombre_cientifico,ByteArray(0), id_categoria)
}
