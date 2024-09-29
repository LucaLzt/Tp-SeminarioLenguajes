package com.example.tp_seminariodelenguajes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var btnModo: ImageButton
    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegistrar: Button
    private lateinit var btnRecordarUsuario: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnModo = findViewById(R.id.btnModo)
        etUsuario = findViewById(R.id.etUsuario)
        etContrasena = findViewById(R.id.etContraseña)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnRecordarUsuario = findViewById(R.id.btnRecordarUsuario)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            btnModo.setImageResource(R.drawable.ic_mode_night)
        } else {
            btnModo.setImageResource(R.drawable.ic_mode)
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegistrarUsuario::class.java)
            startActivity(intent)
        }

        val usuarioRecordado = loadUserPreferences(this)
        if(usuarioRecordado != null) {
            etUsuario.setText(usuarioRecordado.first)
            etContrasena.setText(usuarioRecordado.second)
            btnRecordarUsuario.isChecked = true
        }

        btnLogin.setOnClickListener {
            if(etUsuario.text.toString().isEmpty() || etContrasena.text.toString().isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                if(btnRecordarUsuario.isChecked) {
                    saveUserPreferences(this, etUsuario.text.toString(), etContrasena.text.toString(),
                        true)
                } else {
                    saveUserPreferences(this, "", "", false)
                }
                loginUser(this, etUsuario.text.toString(), etContrasena.text.toString()) { user ->
                    if (user != null) {
                        // Usuario autenticado correctamente
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Usuario no encontrado o contraseña incorrecta
                        Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }
                }
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

// Función para guardar los datos del usuario ("Recordar Usuario")
fun saveUserPreferences(context: Context, username: String, password: String, remember: Boolean) {
    val sharedPref = context.getSharedPreferences(PrefsConstants.PREFS_NAME, Context.MODE_PRIVATE)
    with (sharedPref.edit()) {
        putString(PrefsConstants.KEY_USERNAME, username)
        putString(PrefsConstants.KEY_PASSWORD, password)
        putBoolean(PrefsConstants.KEY_REMEMBER, remember)
        apply()
    }
}

// Función para verificar y recuperar los datos guardados del usuario
fun loadUserPreferences(context: Context): Pair<String?, String?>? {
    val sharedPref = context.getSharedPreferences(PrefsConstants.PREFS_NAME, Context.MODE_PRIVATE)
    val remember = sharedPref.getBoolean(PrefsConstants.KEY_REMEMBER, false)
    return if (remember) {
        val username = sharedPref.getString(PrefsConstants.KEY_USERNAME, null)
        val password = sharedPref.getString(PrefsConstants.KEY_PASSWORD, null)
        Pair(username, password)
    } else {
        null
    }
}

// Función para verificar si existe el usuario en la bd ("Iniciar Sesión")
fun loginUser(context: Context, username: String, password: String, onResult: (User?) -> Unit) {
    val db = AppDatabase.getDatabase(context)
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val user = db.userDao().loginUser(username, password)
            onResult(user)
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onResult(null)
            }
        }
    }
}
