package com.example.ultimocuatri

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog


class SpinnerActividad : AppCompatActivity() {

    private lateinit var txtSeleccion: TextView
    private lateinit var spnItem: Spinner
    private lateinit var btnLimpiar: Button
    private lateinit var btnSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_actividad)

        txtSeleccion = findViewById(R.id.txtSeleccion)
        spnItem = findViewById(R.id.spnItem)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnSalir = findViewById(R.id.btnSalir)

        val list = arrayListOf(
            ItemData("", getString(R.string.msgDescripcion), 0),
            ItemData(getString(R.string.itemAgradecimiento), getString(R.string.msgAgradecimiento), R.drawable.agradecimiento),
            ItemData(getString(R.string.itemAmor), getString(R.string.msgAmor), R.drawable.corazon),
            ItemData(getString(R.string.itemNewYear), getString(R.string.msgNewYear), R.drawable.nuevo),
            ItemData(getString(R.string.itemCanciones), getString(R.string.msgCanciones), R.drawable.canciones)
        )


        val adapter = SpinnerAdapter(this, R.layout.spinner, list)
        spnItem.adapter = adapter

        spnItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View, position: Int, id: Long) {
                if (position == 0) {
                    txtSeleccion.text = ""
                } else {
                    val item = list[position]
                    txtSeleccion.text = "${getString(R.string.msgSeleccion)}: ${item.txtCategoria}"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                txtSeleccion.text = ""
            }
        }

        btnLimpiar.setOnClickListener {
            spnItem.setSelection(0)
            txtSeleccion.text = ""
        }

        btnSalir.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("¿Estás seguro que deseas salir?")
                .setPositiveButton("Aceptar") { _, _ -> finish() }
                .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
                .setNeutralButton("Quizá") { dialog, _ ->
                    Toast.makeText(this, "Quiza mas tarde...", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

    }
}