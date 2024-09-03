package com.example.tp_seminariodelenguajes

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var txtUsuario:TextView
    lateinit var txtViewUsuario:EditText
    lateinit var txtPassword:TextView
    lateinit var txtViewPassword:EditText
    lateinit var btnInicioSesion:Button
    lateinit var btnRegistrar:Button
    lateinit var btnRecordarUsuario:CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtUsuario = findViewById(R.id.txtUsuario)
        txtViewUsuario = findViewById(R.id.txtViewUsuario)
        txtPassword = findViewById(R.id.txtPassword)
        txtViewPassword = findViewById(R.id.txtViewPassword)
        btnInicioSesion = findViewById(R.id.btnInicioSesion)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnRecordarUsuario = findViewById(R.id.btnRecordarUsuario)

    }

}