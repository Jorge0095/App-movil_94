package com.example.ultimocuatri

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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

        iniciarComponentes()
        eventosBotones()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){

        // Relacionar los componentes del Layout con los objetos de Kotlin.
        lblSaludo = findViewById(R.id.lblSaludo)
        txtNombre = findViewById(R.id.txtNombre)
        btnSaludar = findViewById(R.id.btnSaludar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)

    }


    fun eventosBotones(){
        // Codificar el evento del botón Saludar
        btnSaludar.setOnClickListener(View.OnClickListener {
            // Declarar variable
            var strNombre : String = ""
            if (txtNombre.text.toString().contentEquals("")){
                Toast.makeText(applicationContext,
                    "Falto capturar el Nombre", Toast.LENGTH_SHORT).show()
            } else {
                strNombre = " Hola " + txtNombre.text.toString() + " Cómo estás ?"
                // Asignar nombre a Edit Text
                lblSaludo.text = strNombre
            }
        }) // Fin btnSaludar.
        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtNombre.setText("")
            lblSaludo.setText("Mi Primera Aplicación Móvil")

        }) // Fin btnLimpiar.
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
        }) // Fin btnCerrar.
    } // Cierra función eventosBotones.
} // Cierra la clase.