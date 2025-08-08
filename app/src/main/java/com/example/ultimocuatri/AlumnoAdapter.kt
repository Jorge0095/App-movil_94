package com.example.ultimocuatri

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AlumnoAdapter(
    context: Context,
    private val layoutRes: Int,
    private val datos: ArrayList<AlumnoData>
) : ArrayAdapter<AlumnoData>(context, layoutRes, datos) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vista = convertView ?: inflater.inflate(layoutRes, parent, false)

        val imgFoto   = vista.findViewById<ImageView>(R.id.imgFoto)
        val txtNombre = vista.findViewById<TextView>(R.id.txtNombre)
        val txtCarrera= vista.findViewById<TextView>(R.id.txtCarrera)
        val txtMat    = vista.findViewById<TextView>(R.id.txtMatricula)

        val item = datos[position]

        imgFoto.setImageResource(item.fotoRes)
        txtNombre.text   = item.nombre
        txtCarrera.text  = item.carrera
        txtMat.text      = item.matricula

        return vista
    }
}
