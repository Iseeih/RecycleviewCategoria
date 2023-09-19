package com.example.recycleviewcategoria.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.recycleviewcategoria.R
import com.example.recycleviewcategoria.controlador.Adaptador_libros
import com.example.recycleviewcategoria.modelo.libro_buscar
import org.json.JSONArray
import org.json.JSONException

class MainActivity2 : AppCompatActivity() {

    var listalibros: MutableList<libro_buscar> = mutableListOf()

    lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        recyclerview = findViewById(R.id.rvLibros)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this)
        listalibros = ArrayList()

        // resultado = findViewById(R.id.)//error momentaneo
        MostrarResultado()
    }
    fun MostrarResultado() {
        Volley.newRequestQueue(this)
        val url = "http://192.168.1.105:8080/app/buscar_libro.php"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the response string.
                try {
                    val jsonArray = JSONArray(response)
                    val arrayLength = jsonArray.length()
                    for (i in 0 until arrayLength) {
                        val libros = jsonArray.getJSONObject(i)
                        listalibros.add(
                            libro_buscar(
                                libros.getString("nom_lib"),
                                libros.getInt("gra_lib"),
                                libros.getString("fk_cat_lib"),
                                libros.getInt("can_lib"),
                                libros.getString("fk_autor_lib")
                            )
                        )
                    }

                    val myadaptor = Adaptador_libros(applicationContext, listalibros)
                    recyclerview.adapter = myadaptor


                } catch (e: JSONException) {
                    // Manejar la excepción de análisis JSON
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error: VolleyError? ->
                // Handle error
                Toast.makeText(this, "Error: ${error?.message ?: "Unknown error"}", Toast.LENGTH_LONG).show()
            })

        // Agregar la solicitud a la cola de solicitudes
        //queue.add(stringRequest)
        Volley.newRequestQueue(this).add(stringRequest)
    }
}


