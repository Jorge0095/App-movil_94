package com.example.ultimocuatri

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.abs


class Cotizacion : AppCompatActivity() {
    // Variables
    private lateinit var txtCliente: TextView
    private lateinit var txtFolio: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var txtPorPagI: EditText
    private lateinit var rdb12: RadioButton
    private lateinit var rdb24: RadioButton
    private lateinit var rdb36: RadioButton
    private lateinit var rdb48: RadioButton
    private lateinit var txtPagoInicial: TextView
    private lateinit var txtTotalFin: TextView
    private lateinit var txtPagoMensual: TextView

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cotizacion)

        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes() {
        txtCliente = findViewById(R.id.txtCliente) as TextView
        txtFolio = findViewById(R.id.txtFolio) as TextView
        txtDescripcion = findViewById(R.id.txtDescripcion) as EditText
        txtPrecio = findViewById(R.id.txtPrecio) as EditText
        txtPorPagI = findViewById(R.id.txtPorcentaje) as EditText
        txtPagoInicial = findViewById(R.id.txtPagoInicial) as TextView
        txtTotalFin = findViewById(R.id.txtTotalFin) as TextView
        txtPagoMensual = findViewById(R.id.txtPagoMensual) as TextView

        rdb12 = findViewById(R.id.rdb12) as RadioButton
        rdb24 = findViewById(R.id.rdb24) as RadioButton
        rdb36 = findViewById(R.id.rdb36) as RadioButton
        rdb48 = findViewById(R.id.rdb48) as RadioButton

        btnCalcular = findViewById(R.id.btnCalcular) as Button
        btnLimpiar = findViewById(R.id.btnLimpiar) as Button
        btnCerrar = findViewById(R.id.btnCerrar) as Button

        var strCliente: String = intent.getStringExtra("cliente").toString()
        txtCliente.text = strCliente.toString()

        // Agregar al folio
        var folio: Int = abs(CotizacionAcc().generarFolio())
        txtFolio.text = "Folio : " + folio.toString()
    }

    public fun eventosClic() {
        btnCalcular.setOnClickListener( View.OnClickListener {
            // Generar objeto de clase cotizacion
            var cotizacion = CotizacionAcc()
            // Validar
            if (txtDescripcion.text.toString().contentEquals("") ||
                txtPrecio.text.toString().contentEquals("") ||
                txtPorPagI.text.toString().contentEquals("")
            ) {
                Toast.makeText(
                    this, "Falto capturar algun dato",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                // Generar un folio
                txtFolio.text = cotizacion.generarFolio().toString()
                cotizacion.descripcion = txtDescripcion.text.toString()
                cotizacion.precio = txtPrecio.text.toString().toFloat()
                cotizacion.porPagInicial = txtPorPagI.text.toString().toFloat()
                // Plazos
                if (rdb12.isChecked) cotizacion.plazos = 12
                if (rdb24.isChecked) cotizacion.plazos = 24
                if (rdb36.isChecked) cotizacion.plazos = 36
                if (rdb48.isChecked) cotizacion.plazos = 48
                // Calculos
                txtPagoInicial.text = getString(R.string.pinicial) + ": " + cotizacion.calcularPagoInicial().toString()
                txtTotalFin.text = getString(R.string.tfin) + ": " + cotizacion.calcularTotalFin().toString() + ":" + cotizacion.calcularPagoInicial().toString()
                txtPagoMensual.text = getString(R.string.pmensual) + ": " + cotizacion.calcularPagoInicial().toString()
            }
        })

        btnLimpiar.setOnClickListener( View.OnClickListener {
            // Limpiar TextView
            txtFolio.text = ""
            txtPagoInicial.text = getString(R.string.pinicial)
            txtTotalFin.text = getString(R.string.tfin)
            txtPagoMensual.text = getString(R.string.pmensual)
            // Limpiar EditText
            txtDescripcion.setText("")
            txtPrecio.setText("")
            txtPorPagI.setText("")
            // Activar el radio 12 Meses
            rdb12.isChecked = true
        })

        btnCerrar.setOnClickListener( View.OnClickListener {
            finish()
        })
    }
}