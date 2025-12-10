package ba.sum.fsre.loginfirebase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        val session = SessionManager(this)

        val editEmail = findViewById<EditText>(R.id.edit_email)
        val editPassword = findViewById<EditText>(R.id.edit_password)
        val buttonLogin = findViewById<Button>(R.id.button_login)
        val textError = findViewById<TextView>(R.id.text_error)

        if (session.isLoggedIn()) {
            goToWelcomeScreen()
            finish()
            return
        }

        buttonLogin.setOnClickListener {
            val username = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            // Prazna polja
            if (username.isEmpty() || password.isEmpty()) {
                textError.text = "Molimo unesite korisničko ime i lozinku."
            } else  {
                textError.text = ""
                auth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            session.setLoggedIn(true)

                            goToWelcomeScreen()
                            // ovdje ide prelazak na MainActivity itd.
                        } else {
                            textError.text = "Pogrešno korisničko ime ili lozinka."
                        }
                    }

            }

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun goToWelcomeScreen() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
}