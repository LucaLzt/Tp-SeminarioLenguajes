package com.example.tp_seminariodelenguajes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistrarUsuario : AppCompatActivity() {

    private lateinit var btnAtras: ImageButton
    private lateinit var btnModo: ImageButton
    private lateinit var btnConfirmarRegistro: Button
    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var etConfirmarContrasena: EditText

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
        btnModo = findViewById(R.id.btnModo)
        btnConfirmarRegistro = findViewById(R.id.btn_confirmar_registro)
        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContraseña)
        etConfirmarContrasena = findViewById(R.id.etConfirmarContraseña)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            btnModo.setImageResource(R.drawable.ic_mode_night)
        } else {
            btnModo.setImageResource(R.drawable.ic_mode)
        }

        btnAtras.setOnClickListener {
            finish()
        }

        btnConfirmarRegistro.setOnClickListener {
            if(etUsuario.text.toString().isEmpty() || etContrasena.text.toString().isEmpty() || etConfirmarContrasena.text.toString().isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else if(etContrasena.text.toString() != etConfirmarContrasena.text.toString()) {
                Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registro Completo!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        btnModo.setOnClickListener {
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        }

    }
}