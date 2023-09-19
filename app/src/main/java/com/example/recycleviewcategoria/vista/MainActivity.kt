package com.example.recycleviewcategoria.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.recycleviewcategoria.R
import com.example.recycleviewcategoria.controlador.Adaptador
import com.example.recycleviewcategoria.modelo.BuscarCategoria
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    var listacat:MutableList<BuscarCategoria> = mutableListOf()

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.rvCategoria)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=GridLayoutManager(this,2)
        listacat =ArrayList()
        Mostrar()
    }

     fun Mostrar() {
         // Instantiate the RequestQueue.
         val queue = Volley.newRequestQueue(this)
         val url = "http://192.168.1.102:8080/app/buscar_categoria.php"

         // Request a string response from the provided URL.
         val stringRequest = StringRequest(
             Request.Method.GET, url,
             Response.Listener<String> { response ->
                 try {
                     val jsonArray = JSONArray(response)
                     val arrayLength = jsonArray.length()
                     for (i in 0 until arrayLength) {
                         val cate = jsonArray.getJSONObject(i)
                         listacat.add(
                             BuscarCategoria(
                                 cate.getString("nombre_cat"),
                                 cate.getString("descripcion")
                             )
                         )
                     }
                     val myoperador = Adaptador(applicationContext, listacat)
                     myoperador.setOnClickListener(View.OnClickListener { view ->
                         // Aquí puedes manejar el clic en el elemento
                         // Puedes utilizar 'view' para obtener la vista clickeada
                         // También puedes utilizar el método 'getAdapterPosition()' para obtener la posición del elemento clickeado
                         val position = recyclerView.getChildAdapterPosition(view)
                         val nombreCategoria = listacat[position].nombre_cat
                         Toast.makeText(applicationContext, "Nombre de la categoría clickeada: $nombreCategoria", Toast.LENGTH_SHORT).show()
                     })

                     recyclerView.adapter = myoperador



                 } catch (e: JSONException) {
                     e.printStackTrace()
                 }
             },
             Response.ErrorListener { error: VolleyError? ->
                 Toast.makeText(this, "Error: ${error?.message ?: "Unknown error"}", Toast.LENGTH_LONG).show()
             })
         Volley.newRequestQueue(this).add(stringRequest)

    }
}