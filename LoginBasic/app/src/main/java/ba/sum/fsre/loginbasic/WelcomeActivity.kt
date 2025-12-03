package ba.sum.fsre.loginbasic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        val textWelcome = findViewById<TextView>(R.id.text_welcome)
        val buttonLogout = findViewById<Button>(R.id.button_logout)

        // Preuzmi korisničko ime iz Intent-a
        val username = intent.getStringExtra("username") ?: "korisniče"

        textWelcome.text = "Welcome, $username!"

        buttonLogout.setOnClickListener {
            // Obriši stanje prijave iz SharedPreferences
            val sharedPref = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

            val editor = sharedPref.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.remove("username")
            editor.apply()

            // Vrati se na LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}