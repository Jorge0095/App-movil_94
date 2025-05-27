package com.example.ultimocuatri

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.GridLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Monedas : AppCompatActivity() {
    // Declaración de Variables.
    // -Texto-
    private lateinit var txtCantidad : TextView
    private lateinit var lblResultado : TextView
    // Grid Propia
    private lateinit var gridPropio : GridLayout
    private lateinit var eur : CheckBox
    private lateinit var lib : CheckBox
    private lateinit var usd : CheckBox
    private lateinit var cad : CheckBox
    private lateinit var checkboxes : Array<CheckBox>
    // Grid Patrick
    private lateinit var gridPatrick : GridLayout
    private lateinit var spnMoneda : Spinner
    // -Botones-
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button
    private lateinit var switchModo : SwitchCompat
    private lateinit var indicadorModo : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_monedas)

        iniciarComponentes()
        funcionBotones()
        while (gridPropio.visibility == View.VISIBLE) {
            soloUnCheckBox()
            break
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    public fun iniciarComponentes(){
        // Txt
        txtCantidad = findViewById(R.id.txtCantidad)
        lblResultado = findViewById(R.id.lblResultado)
        // Modo Propio
        gridPropio = findViewById(R.id.gridPropio)
        eur = findViewById(R.id.eur)
        lib = findViewById(R.id.lib)
        usd = findViewById(R.id.usd)
        cad = findViewById(R.id.cad)
        checkboxes = arrayOf(eur, lib, usd, cad)
        // Modo Patrick
        gridPatrick = findViewById(R.id.gridPatrick)
        spnMoneda = findViewById(R.id.spnMoneda)
        val items = resources.getStringArray(R.array.monedas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spnMoneda.adapter = adapter
        // Botones
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
        switchModo = findViewById(R.id.switchModo)
        indicadorModo = findViewById(R.id.indicadorModo)
    }

    private fun soloUnCheckBox() {
        val onCheckedChangeListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                for (cb in checkboxes) {
                    if (cb.id != buttonView.id) {
                        cb.isChecked = false
                    }
                }
            }
        }
        for (cb in checkboxes) {
            cb.setOnCheckedChangeListener(onCheckedChangeListener)
        }
    }

    public fun funcionBotones(){
        // Lógica Spinner
        var pos : Int = 0
        spnMoneda.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long) {

                val item = parent?.getItemAtPosition(position).toString()
                Toast.makeText(applicationContext,
                    "El valor es: $item. Posición: $position", Toast.LENGTH_SHORT).show()
                pos = position
                lblResultado.text = ""
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                TODO("Not yet implemented")
            }
        }



        btnCalcular.setOnClickListener(View.OnClickListener {
            // Valores de moneda
            val dolara = resources.getString(R.string.dolara)
            val dolarc = resources.getString(R.string.dolarc)
            val euro = resources.getString(R.string.euro)
            val libra = resources.getString(R.string.libra)
            // Grids
            if (txtCantidad.text.toString().contentEquals("")){
                Toast.makeText(applicationContext, "Faltó capturar la cantidad", Toast.LENGTH_SHORT).show()
            } else {
                val cantidad = txtCantidad.text.toString().toFloat()
                var resultado: Float = 0.0f
                if (gridPropio.visibility == View.VISIBLE) {
                    when (checkboxes.any { it.isChecked }) {
                        eur.isChecked -> {
                            resultado = cantidad / euro.toFloat()
                            val resFormat: String = String.format("%.2f", resultado)
                            lblResultado.text = resFormat
                        }
                        lib.isChecked -> {
                            resultado = cantidad / libra.toFloat()
                            val resFormat: String = String.format("%.2f", resultado)
                            lblResultado.text = resFormat
                        }
                        usd.isChecked -> {
                            resultado = cantidad / dolara.toFloat()
                            val resFormat: String = String.format("%.2f", resultado)
                            lblResultado.text = resFormat
                        }
                        cad.isChecked -> {
                            resultado = cantidad / dolarc.toFloat()
                            val resFormat: String = String.format("%.2f", resultado)
                            lblResultado.text = resFormat
                        }
                        else -> {
                            Toast.makeText(applicationContext, "Seleccione una divisa para la conversión", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    // Estructura de selección múltiple 'when', más simple que 'switch'
                    resultado = when (pos) {
                    0 -> cantidad / dolara.toFloat()
                    1 -> cantidad / dolarc.toFloat()
                    2 -> cantidad / euro.toFloat()
                    3 -> cantidad / libra.toFloat()
                    4 -> cantidad * dolara.toFloat()
                    5 -> cantidad * euro.toFloat()
                    6 -> cantidad * dolarc.toFloat()
                    7 -> cantidad * libra.toFloat()
                    else -> 0.0f
                    }
                    val resFormat: String = String.format("%.2f", resultado)
                    lblResultado.text = resFormat
                }
            }
        })

        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtCantidad.text = ""
            lblResultado.text = getString(R.string.resultado)
        })

        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Monedas")
            builder.setMessage("¿Desea Cerrar la Aplicación?")

            builder.setPositiveButton("Si") { dialog, which ->
                finish()

            }
            builder.setNegativeButton("No") { dialog, which ->
                Toast.makeText(applicationContext,
                    "Cancelar", Toast.LENGTH_SHORT).show()
            }

            builder.setNeutralButton("Quiza") { dialog, which ->
                Toast.makeText(applicationContext,
                    "Quiza", Toast.LENGTH_SHORT).show()
            }

            builder.show()
        })

        switchModo.setOnClickListener(View.OnClickListener {
            if (gridPropio.visibility == View.VISIBLE) {
                gridPropio.visibility = View.GONE
                gridPatrick.visibility = View.VISIBLE
                indicadorModo.text = getString(R.string.SwitchModo1)
            } else {
                gridPropio.visibility = View.VISIBLE
                gridPatrick.visibility = View.GONE
                indicadorModo.text = getString(R.string.SwitchModo2)
            }
        })
    }
}