package com.example.tp_seminariodelenguajes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistrarUsuario : AppCompatActivity() {

    lateinit var btnAtras: ImageButton
    lateinit var btnConfirmarRegistro: Button
    lateinit var etUsuario: EditText
    lateinit var etContraseña: EditText
    lateinit var etConfirmarContraseña: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnAtras = findViewById(R.id.btnAtras)
        btnConfirmarRegistro = findViewById(R.id.btn_confirmar_registro)
        etUsuario = findViewById(R.id.etUsuario)
        etContraseña = findViewById(R.id.etContraseña)
        etConfirmarContraseña = findViewById(R.id.etConfirmarContraseña)

        btnAtras.setOnClickListener {
            finish()
        }

        btnConfirmarRegistro.setOnClickListener {
            if(etUsuario.text.toString().isEmpty() || etContraseña.text.toString().isEmpty() || etConfirmarContraseña.text.toString().isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registro Completo!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }
}