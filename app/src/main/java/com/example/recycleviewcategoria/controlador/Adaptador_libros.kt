package com.example.recycleviewcategoria.controlador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.recycleviewcategoria.R
import com.example.recycleviewcategoria.modelo.libro_buscar


class Adaptador_libros(private val mCtx: Context,
                       private val listalibros: List<libro_buscar>,
                       private val dataSet: Array<String>)
    :
    RecyclerView.Adapter<Adaptador_libros.LibroViewHolder>() {

    constructor(mCtx: Context, listalibros: List<libro_buscar>) : this(mCtx, listalibros, emptyArray())



    class LibroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombreLib: TextView = view.findViewById(R.id.txtNombrelib)
        val txtGradoLib: TextView = view.findViewById(R.id.txtGradoLib)
        val txtGeneroLib: TextView = view.findViewById(R.id.txtGeneroLib)
        val txtCantidadLib: TextView = view.findViewById(R.id.txtCantidadLib)
        val txtAutorLib: TextView = view.findViewById(R.id.txtAutorLib)
        init {
            // Define click listener for the ViewHolder's View.

        }
    }

    //creamos una interface que almacenara la parte del click en mi recycler view


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LibroViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.elementos_libro, viewGroup, false)

        return LibroViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: LibroViewHolder, position: Int) {
        val libro = listalibros[position]

        viewHolder.txtNombreLib.text = libro.nom_lib
        viewHolder.txtGradoLib.text = "Grado: " + libro.gra_lib.toString()
        viewHolder.txtGeneroLib.text = "Género: " + libro.fk_cat_lib.toString()
        viewHolder.txtCantidadLib.text = "Cantidad: " + libro.can_lib.toString()
        viewHolder.txtAutorLib.text = "Autor: " + libro.fk_autor_lib.toString()

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return listalibros.size
    }
}