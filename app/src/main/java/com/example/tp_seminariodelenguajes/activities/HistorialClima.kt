package com.example.tp_seminariodelenguajes.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_seminariodelenguajes.R
import com.example.tp_seminariodelenguajes.WeatherAdapter
import com.example.tp_seminariodelenguajes.WeatherForecast

class HistorialClima : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var btnAtras: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial_clima)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnAtras = findViewById(R.id.btnAtras)

        // Datos de prueba
        val weatherData = listOf(
            WeatherForecast("MAÑANA", "2024-09-14", "30°C", "Soleado", R.drawable.soleado),
            WeatherForecast("TARDE", "2024-09-14", "35°C", "Soleado", R.drawable.soleado),
            WeatherForecast("NOCHE", "2024-09-14", "25°C", "Soleado", R.drawable.soleado),
            WeatherForecast("MAÑANA", "2024-09-13", "23°C", "Nublado", R.drawable.nublado),
            WeatherForecast("TARDE", "2024-09-13", "27°C", "Nublado", R.drawable.nublado),
            WeatherForecast("NOCHE", "2024-09-13", "22°C", "Nublado", R.drawable.nublado),
            WeatherForecast("MAÑANA", "2024-09-12", "20°C", "Lluvioso", R.drawable.lluvia),
            WeatherForecast("TARDE", "2024-09-12", "20°C", "Lluvioso", R.drawable.lluvia),
            WeatherForecast("NOCHE", "2024-09-12", "17°C", "Lluvioso", R.drawable.lluvia),
            WeatherForecast("MAÑANA", "2024-09-11", "-5°C", "Caída de nieve", R.drawable.nevando),
            WeatherForecast("TARDE", "2024-09-11", "-1°C", "Caída de nieve", R.drawable.nevando),
            WeatherForecast("NOCHE", "2024-09-11", "-9°C", "Caída de nieve", R.drawable.nevando),
            WeatherForecast("MAÑANA", "2024-09-10", "15°C", "Parcialmente nublado", R.drawable.parcialmente_nublado),
            WeatherForecast("TARDE", "2024-09-10", "17°C", "Parcialmente nublado", R.drawable.parcialmente_nublado),
            WeatherForecast("NOCHE", "2024-09-10", "11°C", "Parcialmente nublado", R.drawable.parcialmente_nublado),
            WeatherForecast("MAÑANA", "2024-09-9", "24°C", "Soleado pero con nubarrones", R.drawable.lluvia_con_sol),
            WeatherForecast("TARDE", "2024-09-9", "25°C", "Soleado pero con nubarrones", R.drawable.lluvia_con_sol),
            WeatherForecast("NOCHE", "2024-09-9", "24°C", "Soleado pero con nubarrones", R.drawable.lluvia_con_sol),
            WeatherForecast("MAÑANA", "2024-09-8", "17°C", "Tormenta eléctrica", R.drawable.tormenta_electrica),
            WeatherForecast("TARDE", "2024-09-8", "19°C", "Tormenta eléctrica", R.drawable.tormenta_electrica),
            WeatherForecast("NOCHE", "2024-09-8", "16°C", "Tormenta eléctrica", R.drawable.tormenta_electrica),
            WeatherForecast("MAÑANA", "2024-09-7", "11°C", "Tormenta eléctrica", R.drawable.tormenta_electrica),
            WeatherForecast("TARDE", "2024-09-7", "14°C", "Tormenta eléctrica", R.drawable.tormenta_electrica),
            WeatherForecast("NOCHE", "2024-09-7", "12°C", "Tormenta eléctrica", R.drawable.tormenta_electrica),
            WeatherForecast("MAÑANA", "2024-09-6", "20°C", "Lluvioso", R.drawable.lluvia),
            WeatherForecast("TARDE", "2024-09-6", "26°C", "Lluvioso", R.drawable.lluvia),
            WeatherForecast("NOCHE", "2024-09-6", "23°C", "Lluvioso", R.drawable.lluvia)
        )

        // Inicializar el RecyclerView
        weatherRecyclerView = findViewById(R.id.rvWeather)
        weatherAdapter = WeatherAdapter(weatherData)

        weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        weatherRecyclerView.adapter = weatherAdapter

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

        btnAtras.setOnClickListener {
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.itemModoDia) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else if (item.itemId == R.id.itemModoNoche) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        recreate()
        return super.onOptionsItemSelected(item)
    }

}