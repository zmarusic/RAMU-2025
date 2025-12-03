package ba.sum.fsre.loginbasic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    // Hardkodirani ispravni korisnik
    private val validUsername = "student"
    private val validPassword = "lozinka123"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editUsername = findViewById<EditText>(R.id.edit_username)
        val editPassword = findViewById<EditText>(R.id.edit_password)
        val checkRememberMe = findViewById<CheckBox>(R.id.check_remember_me)
        val buttonLogin = findViewById<Button>(R.id.button_login)
        val textError = findViewById<TextView>(R.id.text_error)

        // SharedPreferences
        val sharedPref = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

        // Provjeri je li korisnik već prijavljen (Zapamti me)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)
        val savedUsername = sharedPref.getString("username", null)

        if (isLoggedIn && savedUsername != null) {
            // Ako je već prijavljen, odmah na WelcomeActivity
            goToWelcomeScreen(savedUsername)
            finish()
            return
        }

        buttonLogin.setOnClickListener {
            val username = editUsername.text.toString().trim()
            val password = editPassword.text.toString().trim()

            // Prazna polja
            if (username.isEmpty() || password.isEmpty()) {
                textError.text = "Molimo unesite korisničko ime i lozinku."
            } else if (username == validUsername && password == validPassword) {
                textError.text = ""
                if (checkRememberMe.isChecked) {

                    if (checkRememberMe.isChecked) {
                        val editor = sharedPref.edit()
                        editor.putBoolean("isLoggedIn", true)
                        editor.putString("username", username)
                        editor.apply()
                    }
                }
                goToWelcomeScreen(username)
            } else {
                textError.text = "Pogrešno korisničko ime ili lozinka."
            }

        }









        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun goToWelcomeScreen(username: String) {
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
    }
}