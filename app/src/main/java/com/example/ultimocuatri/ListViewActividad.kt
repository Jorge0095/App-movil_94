package com.example.ultimocuatri

import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListViewActividad : AppCompatActivity() {
    private lateinit var lstAlumnos: ListView
    private lateinit var btnCerrar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var txtSel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view_actividad)
        iniciarComponentes()
        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom); insets
        }
    }

    private fun iniciarComponentes() {
        lstAlumnos = findViewById(R.id.lstAlumnos)
        btnCerrar  = findViewById(R.id.btnCerrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        txtSel     = findViewById(R.id.txtSel)

        val datos = arrayListOf(
            AlumnoData(
                getString(R.string.al1_nom),
                getString(R.string.al1_matr),
                getString(R.string.al1_carrera),
                R.drawable.chuy
            ),
            AlumnoData(
                getString(R.string.al2_nom),
                getString(R.string.al2_matr),
                getString(R.string.al2_carrera),
                R.drawable.jorge
            ),
            AlumnoData(
                getString(R.string.al3_nom),
                getString(R.string.al3_matr),
                getString(R.string.al3_carrera),
                R.drawable.marco
            ),
            AlumnoData(
                getString(R.string.al4_nom),
                getString(R.string.al4_matr),
                getString(R.string.al4_carrera),
                R.drawable.kevin
            )
        )

        lstAlumnos.adapter = AlumnoAdapter(this, R.layout.view, datos)
    }

    private fun eventosClick() {
        btnCerrar.setOnClickListener { finish() }

        btnLimpiar.setOnClickListener {
            lstAlumnos.clearChoices()
            (lstAlumnos.adapter as BaseAdapter).notifyDataSetChanged()
            txtSel.text = getString(R.string.noSelect)
        }

        lstAlumnos.setOnItemClickListener { _, _, position, _ ->
            val alumno = lstAlumnos.adapter.getItem(position) as AlumnoData
            txtSel.text = getString(R.string.msgSeleccion) + ": " + alumno.nombre
        }
    }
}
