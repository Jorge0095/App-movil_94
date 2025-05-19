package com.example.ultimocuatri

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IMC : AppCompatActivity() {
    private lateinit var txtAltura : EditText
    private lateinit var txtPeso : EditText
    private lateinit var lblResultado : TextView
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)

        iniciarComponentes()
        eventosBotones()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        txtAltura = findViewById(R.id.txtAltura)
        txtPeso = findViewById(R.id.txtPeso)
        lblResultado = findViewById(R.id.lblResultado)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
    }

    fun eventosBotones(){
        btnCalcular.setOnClickListener(){
            // iniciar variables
            var altura : Double = 0.0
            var peso : Double = 0.0
            var imc : Double = 0.0
            // Asignar valores
            altura = txtAltura.text.toString().toDouble()
            peso = txtPeso.text.toString().toDouble()
            imc = peso / (altura * altura)
            // Mostrar resultado
            lblResultado.text = "Tu IMC es de: " + imc.toString()
        }
        btnLimpiar.setOnClickListener(){
            txtAltura.setText("")
            txtPeso.setText("")
            lblResultado.text = ""
        }
        btnCerrar.setOnClickListener(){
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
        }
    }
}