package com.example.ultimocuatri

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Convertir : AppCompatActivity() {
    // Declarar Variables de los Elementos
    private lateinit var txtCantidad : TextView
    private lateinit var txtResultado : TextView
    private lateinit var rdbCel : RadioButton
    private lateinit var rdbFar : RadioButton
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    public fun iniciarComponentes(){
        txtCantidad = findViewById(R.id.txtCantidad)
        txtResultado = findViewById(R.id.txtResultado)
        rdbCel = findViewById(R.id.rdbCel)
        rdbFar = findViewById(R.id.rdbFar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
        btnCalcular = findViewById(R.id.btnCalcular)
    }

    public fun funcionBotones(){
        btnCalcular.setOnClickListener(View.OnClickListener {
            // Declarar Variables
            var grados : Double = 0.0
            var resultado : Double = 0.0
            // Asignar valores
            grados = txtCantidad.text.toString().toDouble()
            // Proceder con la conversión
            if(rdbCel.isChecked){
                grados = (grados * 1.8) + 32
                txtResultado.text = grados.toString()
            }else if(rdbFar.isChecked){
                grados = (grados - 32) / 1.8
                txtResultado.text = grados.toString()
            }
        })
        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtCantidad.setText("")
            txtResultado.setText("")
            txtResultado.setText("@string/resultado")
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Hola ")
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_convertir)

        iniciarComponentes()
        funcionBotones()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}