package com.example.recycleviewcategoria.modelo

class libro_buscar(
    val nom_lib: String,
    val gra_lib: Int,
    val fk_cat_lib: String,
    val can_lib: Int,
    val fk_autor_lib: String,

    ) {
    constructor() : this("", 0, "", 0, "")
}