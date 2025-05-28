package com.example.ultimocuatri

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MenuActivity : AppCompatActivity() {

    private lateinit var crvHola : CardView
    private lateinit var crvImc : CardView
    private lateinit var crvConvertir : CardView
    private lateinit var crvMonedas : CardView
    private lateinit var crvCotizacion : CardView
    private lateinit var crvSpiner : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        // Llamar funciones
        iniciarComponentes()
        eventosBotones()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun iniciarComponentes(){
        crvHola = findViewById(R.id.crvHola) as CardView
        crvImc = findViewById(R.id.crvImc) as CardView
        crvConvertir = findViewById(R.id.crvConvertir) as CardView
        crvMonedas = findViewById(R.id.crvMonedas) as CardView
        crvCotizacion = findViewById(R.id.crvCotizacion) as CardView
        crvSpiner = findViewById(R.id.crvSpiner) as CardView
    }
    fun eventosBotones(){
        crvHola.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
        crvImc.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, IMC::class.java)
            startActivity(intent)
        })
        crvConvertir.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Convertir::class.java)
            startActivity(intent)
        })
        crvMonedas.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Monedas::class.java)
            startActivity(intent)
        })
        crvCotizacion.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Cliente::class.java)
            startActivity(intent)
        })

    }
}
