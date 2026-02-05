package villalba.victoria.miniweather_villalbav

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var weatherIndex = 0 // controlar el clima activo

    private val weatherList = listOf(
        Pair("Sunny", R.drawable.ic_sunny),
        Pair("Cloudy", R.drawable.ic_cloudy),
        Pair("Rainy", R.drawable.ic_rainy),
        Pair("Snowy", R.drawable.ic_snowy),
        Pair("Stormy", R.drawable.ic_stormy),
        Pair("Windy", R.drawable.ic_windy)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvGreeting: TextView = findViewById<TextView>(R.id.tvGreeting)
        val tvCity: TextView = findViewById<TextView>(R.id.tvCity)
        val tvTemperature: TextView = findViewById<TextView>(R.id.tvTemperature)
        val tvWeather: TextView = findViewById<TextView>(R.id.tvWeather)
        val ivWeather: ImageView = findViewById<ImageView>(R.id.ivWeather)
        val btnChangeWeather: Button = findViewById<Button>(R.id.btnChangeWeather)
        val btnNextActivity: Button = findViewById<Button>(R.id.btnNextActivity)

        tvGreeting.setText("Buen día!")
        tvCity.setText("Sonora")
        tvTemperature.setText("32 °C")

        updateWeatherUI(tvWeather, ivWeather)
        btnChangeWeather.setOnClickListener {
            weatherIndex = (weatherIndex + 1) % weatherList.size // if the index comes to the end of the list, it places te index at te first
            updateWeatherUI(tvWeather, ivWeather)
        }

        btnNextActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    fun updateWeatherUI(tvWeather: TextView, ivWeather: ImageView){
        val currentWeather = weatherList[weatherIndex]
        tvWeather.text = currentWeather.first
        ivWeather.setImageResource(currentWeather.second)
    }
}