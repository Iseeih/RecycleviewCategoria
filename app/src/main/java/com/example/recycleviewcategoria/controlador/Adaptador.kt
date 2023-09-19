package com.example.recycleviewcategoria.controlador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewcategoria.R
import com.example.recycleviewcategoria.modelo.BuscarCategoria


class Adaptador(private val mCtx: Context,
                private val listcategoria: List<BuscarCategoria>,
                private val dataSet: Array<String>) :
        RecyclerView.Adapter<Adaptador.CategoriaViewHolder>(),View.OnClickListener {

        constructor(mCtx: Context,listcategoria: List<BuscarCategoria>):this(mCtx,listcategoria,emptyArray())

        private var clickListener: View.OnClickListener? = null


        class CategoriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                val TxtNombre:TextView=view.findViewById(R.id.TxtNombreCat)
                val TxtDescripcion:TextView=view.findViewById(R.id.TxtDescripcion)

                init {
                        // Define click listener for the ViewHolder's View.

                }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CategoriaViewHolder {
                // Create a new view, which defines the UI of the list item
                val view = LayoutInflater.from(viewGroup.context)
                        .inflate(R.layout.elementos_categoria, viewGroup, false)
                view.setOnClickListener(this)

                return CategoriaViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: CategoriaViewHolder, position: Int) {

                val cat=listcategoria[position]

                viewHolder.TxtNombre.text=cat.nombre_cat
                viewHolder.TxtDescripcion.text=cat.descripcion


        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount():Int{ return listcategoria.size}

        fun setOnClickListener(listener: View.OnClickListener) {
                this.clickListener=listener
        }

        override fun onClick(p0: View?) {
                if (clickListener!=null){
                        clickListener?.onClick(p0)
                }
        }

}
