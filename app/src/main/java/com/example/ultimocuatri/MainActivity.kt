package com.example.ultimocuatri

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Declaración de Objetos
    private lateinit var lblSaludo : TextView
    private lateinit var txtNombre : EditText
    private lateinit var btnSaludar : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        lblSaludo = findViewById(R.id.lblSaludo)
        txtNombre = findViewById(R.id.txtNombre)
        btnSaludar = findViewById(R.id.btnSaludar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
    }
    fun eventosBotones(){
        // Codificar el evento del botón Saludar\
        btnSaludar.setOnClickListener(View.OnClickListener {
            // Declarar variable
            var strNombre : String = ""
            // Validación
            if (txtNombre.text.toString().contains("")){
                Toast.makeText(applicationContext,
                    "Falto capturar información", Toast.LENGTH_SHORT).show()
            } else {
                strNombre = " Hola " + txtNombre.text.toString() + " Cómo estás ?"
                lblSaludo.text = strNombre
            }
        })
        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtNombre.setText("")
            lblSaludo.setText("")
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
}